﻿import lang.*

class MethodsLoader {
	private static var _lol = new FuncInvoker(init)
	private static function init() {
		_global.setInterval(load, 3000)
		_global.setTimeout(load, 0)
	}
	
	static var loaded = false
	static var onFirstLoad: Listener = new Listener()
	
	static function load(onLoad: Function) { 
		_global._rootUrl.request("methods")
			.send(function(data: String){
				var methods = data.split("\n")
				if (!Objects.equals(methods, _global._methods)) { 
					new Logger("stage").trace(methods.length + " methods available")
					_global._methods = methods
					
					if (!loaded) {
						loaded = true
						onFirstLoad.invoke()
					}
					onLoad() 
				}
			})
			
		_global._rootUrl.request("defparams")
			.send(function(data: String){
				var res = {}
				data.split(",").map(function(part: String){
					var blocks = part.split(":")
					res[blocks[0]] = blocks[1]
				})										
				_global._defaultParams = res
			})
	}
}