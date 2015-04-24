; BlockType.bb
; Stuff dealing with different block types
; Michael Hitchens (FootBallHead)
; --------------------------------------------

; TODO
; Add health
; Item to drop

; The Type to hold Block values
Type BlockType
	Field name$
	Field top.RGBColor
	Field bottom.RGBColor
	Field side.RGBColor
	Field alpha#
	Field health
End Type

; Predefined Block types
Global BLOCK_BRICK.BlockType = CreateBlockType( "Brick" , RGB_BRICK_RED , RGB_BRICK_RED , RGB_BRICK_RED )
Global BLOCK_DIRT.BlockType = CreateBlockType( "Dirt" , RGB_BROWN , RGB_BROWN , RGB_BROWN )
Global BLOCK_GLASS.BlockType = CreateBlockType( "Glass" , RGB_CYAN , RGB_CYAN , RGB_CYAN , 0.4 )
Global BLOCK_GRASS.BlockType = CreateBlockType( "Grass" , RGB_GRASS_GREEN , RGB_BROWN , RGB_BROWN )
Global BLOCK_STONE.BlockType = CreateBlockType( "Stone" , RGB_GRAY , RGB_GRAY , RGB_GRAY )

; Create and return a new Block type
Function CreateBlockType.BlockType( name$ , top.RGBColor , bot.RGBColor , sid.RGBColor , alpha#=1.0)
	Local b.BlockType = New BlockType
	b\name$ = name$
	b\top = top
	b\bottom = bot
	b\side = sid
	b\alpha# = alpha#
	Return b
End Function

; Various Functions to set or return Block values
Function BlockTypeName$( b.BlockType )
	Return b\name$
End Function

Function BlockTypeTop.RGBColor( b.BlockType )
	Return b\top
End Function

Function BlockTypeBottom.RGBColor( b.BlockType )
	Return b\bottom
End Function

Function BlockTypeSide.RGBColor( b.BlockType )
	Return b\side
End Function

Function BlockTypeAlpha#( b.BlockType )
	Return b\alpha#
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D