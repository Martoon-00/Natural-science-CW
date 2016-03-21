﻿import lang.*
import net.*
import globals.*

class SolutionKeeper {
 	private static var CHECK_DELAY: Number = 100
	private static var LOAD_LIMIT: Number = 9999
	private var BLOCK_SIZE: Number
	
	private var request: Request
	private var params: Object
	
	private var closed: Boolean = false
	var onUpdate: Listener
	
	private var data: Array
	private var method: String
	
	function SolutionKeeper(request: Request, method: String, params: Object){ 
		var _this = this
		if (params == undefined)
			throw new Error("Passed undefined parameters in SolutionKeeper constructor")
		this.params = params.copy({ method: method })
		
		this.request = request
		this.method = method
		data = new Array()
		BLOCK_SIZE = int(params.loadSpeed)
		if (BLOCK_SIZE == 0)
			BLOCK_SIZE = Math.max(int(20 / (0.1 / params.dz)), 1)
		
		onUpdate = new Listener()
		
		check()
	}
	
	function check(): Void { 
		if (data.length > LOAD_LIMIT) return
		var _this = this
		
		var p = params.copy({ 
			from: data.length, 
			num: BLOCK_SIZE 
		})
		request.send(function(data: String){ 
			if (!closed) 
				_this.fillData.call(_this, parseData(data)) 			
		}, p)
		
		if (!closed) 
			_global.setTimeout(function(){ _this.check() }, CHECK_DELAY)
	}
	
	private function fillData(received: Object): Void {  
		var blockIndex:Number = received.from
		var receivedData: Array = received.data
		var dataSize = data.length
		for (var i = 0; i < BLOCK_SIZE; i++) { 
			if (data.length <= i + blockIndex) {
				data[i + blockIndex] = receivedData[i]
			}
		}
		
		if (data.length > dataSize){ 
			onUpdate.invoke(data.length)
		}
	}
	
	private static function parseData(s: String) {  // converts to data array [index in block][x]
		function parseBlock(block: String) {
			var res = {}
			var lines = block.split("\n")
			res.zSections = lines.slice(0, -1).map(function(line) {
				var vals = line.split(" ").map(function(v){ return Number(v.replace(",", ".")) })
				return { t: vals[0], x: vals[1], w: vals[2] }
			})
			res.vf = Number(lines[lines.length - 1])
			return res
		}
		
		var blocks = s.split("\n\n")
		var curData = blocks.slice(0, -1).map(parseBlock)		
		var extra = blocks[blocks.length - 1].split(" ").map(function(v){ return Number(v) })
		return { data: curData, from: extra[0] }
	}
	
	private static function paramsToString(params: Object) {
		var s: String = ""
		for (var i in params) {
			var p = params[i]
			s += i + ":" + p[i] + ","
		}
		return s.slice(0, -1)
	}
	
	function available(): Number { return data.length }
	function get(index: Number) { return data[index] }
	
	function close(): Void {
		closed = true
	}
	
}