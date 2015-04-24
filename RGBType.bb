; RGBType.bb
; Functions to deal with RGB values
; Michael Hitchens (FootBallHead)
; ------------------------------------------------------

Type RGBColor
	Field red , green , blue
End Type

Global RGB_WHITE.RGBColor = CreateRGBColor( 255 , 255 , 255 )
Global RGB_LIGHT_GRAY.RGBColor = CreateRGBColor( 192 , 192 , 192 )
Global RGB_GRAY.RGBColor = CreateRGBColor( 128 , 128 , 128 )

Global RGB_ORANGE.RGBColor = CreateRGBColor( 255 , 128 , 0 )
Global RGB_GREEN.RGBColor = CreateRGBColor( 0 , 255 , 0 )
Global RGB_CYAN.RGBColor = CreateRGBColor( 0 , 255 , 255 )
Global RGB_SKY_BLUE.RGBColor = CreateRGBColor( 0 , 128 , 255 )
Global RGB_BROWN.RGBColor = CreateRGBColor( 128 , 64 , 0 )

Global RGB_BRICK_RED.RGBColor = CreateRGBColor( 255 , 96 , 0 )
Global RGB_GRASS_GREEN.RGBColor = CreateRGBColor( 0 , 255 , 64 )

Function CreateRGBColor.RGBColor( r , g , b )
	Local rgb.RGBColor = New RGBColor
	rgb\red = r
	rgb\green = g
	rgb\blue = b
	Return rgb
End Function

Function RGBColorRed( rgb.RGBColor )
	Return rgb\red
End Function

Function RGBColorGreen( rgb.RGBColor )
	Return rgb\green
End Function

Function RGBColorBlue( rgb.RGBColor )
	Return rgb\blue
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D