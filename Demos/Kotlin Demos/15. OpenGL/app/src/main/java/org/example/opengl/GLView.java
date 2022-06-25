package org.example.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

class GLView extends GLSurfaceView {
  private final GLRenderer renderer;

  GLView(Context context) {
    super(context);

    // Uncomment this to turn on error-checking and logging
    //setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);

    setEGLConfigChooser(8, 8, 8, 8, 16, 0);
      
    renderer = new GLRenderer(context);
    setRenderer(renderer);
  }
}
