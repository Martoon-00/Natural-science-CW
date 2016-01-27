﻿import lang.*
import util.motion.*
import coordinates.*

class lang.MovieClips {
	private static var _lol = new FuncInvoker(init)
	private static function init() {
		var proto = MovieClip.prototype
		
		proto.setMotion = function(mome: MotionManager, target: Function): MovieClip {
			var motion = this._motion = new Motion(this, mome, target)
			Functions.makeMultiListener(this, "onUnload", function(){ motion.unregister() })
			return this
		}
		
		proto.__pos__
		proto.addProperty("_pos", function(){ 
			if (this.__pos__ != undefined) {
				return this.__pos__
			} else {
				return this.__pos__ = new Coord(this)
			}
		}, function(v: Coord) {
			if (v == undefined) throw new Error("Attempt to place " + this + " at undefined position")
			this.__pos__ = v
			this._x = v.x
			this._y = v.y
		})
		proto.place = function(v: Coord): MovieClip { 
			this._pos = v
			return this
		}
		
		proto.drawer = function(): Drawer { return new Drawer(this) }
		
		proto.addListener = function(prop: String, listener: Function) { 
			Functions.makeMultiListener(this, prop, listener)
		}
		
		proto.notDead = function(){ return _target != "" }
		
		_global.ASSetPropFlags(proto, null, 0x7)
	}
	
	static function getAvailableDepth(mc: MovieClip, startDepth: Number){
		for (var depth = Optional.of(startDepth).orElse(1000); depth < 1e5; depth++){ 
			if (mc.getInstanceAtDepth(depth) == null) return depth
		}
	}
	
	static function createEmptyMovieClip(parent: MovieClip, name: String, startDepth: Number){
		return parent.createEmptyMovieClip(name, getAvailableDepth(parent, startDepth))
	}
	
	static function attachMovie(parent: MovieClip, mcInfo, mcName: String, startDepth: Number, params: Object) {
		if (!(mcInfo instanceof MovieClipInfo)) mcInfo = new MovieClipInfo(mcInfo)
		return parent.attachMovie(mcInfo.name, mcName, getAvailableDepth(parent, startDepth), Objects.createCopy(mcInfo.param, params))
	}
	
	static function attachUniqueMovie(parent: MovieClip, libName, startDepth: Number, params: Object) {
		var id = _global.Counter["attachUniqueMovie_" + parent] 
		if (startDepth == null) startDepth = 0
		return attachMovie(parent, libName, "unique_" + id, startDepth, params)
	}
	
	static function createUniqueMovieClip(parent: MovieClip, startDepth: Number) {
		if (startDepth == null) startDepth = 0
		return createEmptyMovieClip(parent, "unique_" + _global.Counter["createUniqueMovieClip_" + parent], startDepth)
	}
	
}