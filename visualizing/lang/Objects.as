﻿import lang.*

class lang.Objects {
	private static var _lol = new FuncInvoker(init)
	private static function init() { 
		var proto = Object.prototype
		
		proto.size = function() {
			var k = 0
			iterate(this, function(){ k++ })
			return k
		}
		
		proto.isEmpty = function(): Boolean {
			return size() == 0
		}
	
		proto.print = function() { 
			trace("<value> -> " + this)
			iterate(this, function(name, value){ trace(name + " -> " + value) })
			trace("---// end of print //---")
			return this
		}
		
		proto.set = function(): Object {
			for (var i = 0; i < arguments.length; i += 2) {
				this[arguments[i]] = arguments[i + 1]
			}
			return this
		}
		
		proto.map = function(f: Function): Object {
			var res = new Object()
			for (var i in this) res[i] = f(this[i])
			return res
		}
		
		proto.copy = function(dist: Object) {
			if (dist == undefined) dist = {}
			iterate(this, function (name, value){ dist[name] = value })
			return dist	
		}
		
		proto.forEach = function(f: Function) { iterate(this, f) }
		
		proto.equals = function(other: Object): Boolean {
			return Objects.equals(this, other)
		}
		
		proto.trace = function() { trace(this); return this }
	
		proto.defunc = function() { return this }
		
		proto.funcWrap = function() { 
			var _this = this
			return function(){ return _this } 
		}
		
		_global.ASSetPropFlags(proto, null, 0x7)

	}
	
	
	static function createCopy(): Object {
		var r = new Object()
		for (var i = 0; i < arguments.length; i++)
			arguments[i].copy(r)
		return r
	}
	
	static function iterate(src: Object, f: Function): Void {
		for (var i in src) f(i, src[i])
	}
	
	static function reverseIterate(src: Object, f: Function): Void {
		var a = new Array()
		for (var i in src) a.push(src[i])
		iterate(a, f)
	}
	
	static function size(src: Object): Number {
		var k = 0
		iterate(src, function(){ k++ })
		return k
	}
	
	static function isEmpty(src: Object): Boolean {
		return size(src) == 0
	}
	
	static function print(src: Object): Void {
		iterate(src, function(name, value){ trace(name + " -> " + value) })
		trace("---")
	}
	
	static function remove(src: Object): Object {
		for (var i = 1; i < arguments.length; i++){
			delete src[arguments[i]]
		}
		return src
	}
	
	static function create(): Object {
		var res = new Object()
		for (var i = 0; i < arguments.length; i += 2) {
			res[arguments[i]] = arguments[i + 1]
		}
		return res
	}
	
	static function equals(a: Object, b: Object): Boolean {
		if (a == undefined || b == undefined) return false
		var res = true
		Objects.iterate(a, function(name, value){ res = res && (value == b[name]) })
		Objects.iterate(b, function(name, value){ res = res && (value == a[name]) })
		return res
	}
}