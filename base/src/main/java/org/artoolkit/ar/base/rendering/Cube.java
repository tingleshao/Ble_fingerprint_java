/*
 *  Cube.java
 *  ARToolKit5
 *
 *  This file is part of ARToolKit.
 *
 *  ARToolKit is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ARToolKit is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with ARToolKit.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  As a special exception, the copyright holders of this library give you
 *  permission to link this library with independent modules to produce an
 *  executable, regardless of the license terms of these independent modules, and to
 *  copy and distribute the resulting executable under terms of your choice,
 *  provided that you also meet, for each linked independent module, the terms and
 *  conditions of the license of that module. An independent module is a module
 *  which is neither derived from nor based on this library. If you modify this
 *  library, you may extend this exception to your version of the library, but you
 *  are not obligated to do so. If you do not wish to do so, delete this exception
 *  statement from your version.
 *
 *  Copyright 2015 Daqri, LLC.
 *  Copyright 2011-2015 ARToolworks, Inc.
 *
 *  Author(s): Julian Looser, Philip Lamb
 *
 */

package org.artoolkit.ar.base.rendering;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLUtils;

import org.artoolkit.ar.base.R;

/**
 * Simple class to render a coloured cube.
 */
public class Cube {

	private FloatBuffer	mVertexBuffer;
    private FloatBuffer	mColorBuffer;
    private ByteBuffer	mIndexBuffer;

	private FloatBuffer mTextureBuffer;

	private Bitmap bitmap;
	private int mTextureId = -1;
	private boolean mShouldLoadTexture = true;

	float colors[];
    
    public Cube() {
    	this(1.0f);
    }
    
	public Cube(float size) {
		this(size, 0.0f, 0.0f, 0.0f);
	}
	
	public Cube(float size, float x, float y, float z) {
		setArrays(size, x, y, z);
	}

	public void setBitmap(Bitmap bmp) {
		mShouldLoadTexture = false;

		this.bitmap = bmp;
	}

	public void setColors(float[] colors) {
		this.colors = colors;
		mColorBuffer = RenderUtils.buildFloatBuffer(colors);
	}

	public void setArrays(float size, float x, float y, float z) {

		float hs = size / 2.0f;

		float vertices[] = { 
			x - hs, y - hs, z - hs, // 0
			x + hs, y - hs, z - hs, // 1
			x + hs, y + hs, z - hs, // 2
			x - hs, y + hs, z - hs, // 3
			x - hs, y - hs, z + hs, // 4
			x + hs, y - hs, z + hs, // 5
			x + hs, y + hs, z + hs, // 6
			x - hs, y + hs, z + hs, // 7
		};

		float[] texturecoordinate =
				{
						// Front face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,

						// Right face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,

						// Back face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,

						// Left face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,

						// Top face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,

						// Bottom face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,
				};


		if (colors == null) {
			float c = 1.0f;
			colors = new float[]{
					0, 0, 0, c, // 0 black
					c, 0, 0, c, // 1 red
					c, c, 0, c, // 2 yellow
					0, c, 0, c, // 3 green
					0, 0, c, c, // 4 blue
					c, 0, c, c, // 5 magenta
					c, c, c, c, // 6 white
					0, c, c, c, // 7 cyan
			};
		}
//		float colors[] = {
//			0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//				0, 0, 0, c, // 0 black
//		};

		byte indices[] = { 
			0, 4, 5, 	0, 5, 1, 
			1, 5, 6, 	1, 6, 2, 
			2, 6, 7, 	2, 7, 3, 
			3, 7, 4, 	3, 4, 0, 
			4, 7, 6, 	4, 6, 5, 
			3, 0, 1, 	3, 1, 2 
		};

		mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
		mColorBuffer = RenderUtils.buildFloatBuffer(colors);
		mIndexBuffer = RenderUtils.buildByteBuffer(indices);


		ByteBuffer byteBuf = ByteBuffer.allocateDirect(
				texturecoordinate.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		mTextureBuffer = byteBuf.asFloatBuffer();
		mTextureBuffer.put(texturecoordinate);
		mTextureBuffer.position(0);
    }


	private void loadGLTexture(GL10 gl) {
		// Generate one texture pointer...
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		mTextureId = textures[0];

		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

		// Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		// Use the Android GLUtils to specify a two-dimensional texture image
		// from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	}


    public void draw(GL10 gl) {
		// texture

		// Tell OpenGL to generate textures.
		if (mShouldLoadTexture) {
			loadGLTexture(gl);
			mShouldLoadTexture = false;
		}

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

		GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
		GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

		GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
		GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

		GLES10.glDrawElements(GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);

		GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
		GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

		// textures
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

}