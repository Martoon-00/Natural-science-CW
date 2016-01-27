﻿import coordinates.*

class lang.Transform {
	var xx: Number
	var xy: Number
	var x_: Number
	var yx: Number
	var yy: Number
	var y_: Number
	
	function Transform(xx: Number, xy: Number, x_: Number, yx: Number, yy: Number, y_: Number) {
		this.xx = xx
		this.xy = xy
		this.yx = yx
		this.yy = yy
		this.x_ = x_
		this.y_ = y_
	}
	
	function applyX(x: Number, y: Number): Number { return xx * x + xy * y + x_ }
	function applyY(x: Number, y: Number): Number { return yx * x + yy * y + y_ }
	
	function compose(other: Transform): Transform {
		return new Transform(xx * other.xx + xy * other.yx, xx * other.xy + xy * other.yy, xx * other.x_ + xy * other.y_ + x_,
							 yx * other.xx + yy * other.yx, yx * other.xy + yy * other.yy, yx * other.x_ + yy * other.y_ + y_)
	}
	function then(other: Transform): Transform { return other.compose(this) }
	
	static var E = new Transform(1, 0, 0, 0, 1, 0)
	
	static function TURN(angle: Number) {
		return new Transform(Math.cos(angle), Math.sin(angle), 0, -Math.sin(angle), Math.cos(angle), 0)
	}
	static function ROTATE(angle: Number) { return Transform.TURN(angle / 180 * Math.PI) }
	
	static function MOVE() {
		var c = new Coord(arguments)
		return new Transform(1, 0, c.x, 0, 1, c.y)
	}
	
	static function AROUND(x: Number, y: Number, angle: Number): Transform {
		return MOVE(-x, -y).then(TURN(angle)).then(MOVE(x, y))
	}
	
	static function SCALE(x: Number, y: Number) {
		if (y == undefined) y = x
		return new Transform(x, 0, 0, 0, y, 0)
	}
}