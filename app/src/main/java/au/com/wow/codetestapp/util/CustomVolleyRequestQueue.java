package au.com.wow.codetestapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @FileName CustomVolleyRequestQueue.java
 * @Purpose Custom Class maintaining the request queue associated with the volley framework.
 * @RevisionHistory Created
 */
public class CustomVolleyRequestQueue {

    private static CustomVolleyRequestQueue mInstance;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;

    private CustomVolleyRequestQueue(Context context) {
        this.context = context;
        this.mRequestQueue = getRequestQueue();
        this.imageLoader = new ImageLoader(mRequestQueue,new LruBitmapCache(context));
    }

    public static synchronized CustomVolleyRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CustomVolleyRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
             mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    /**
     * Custom class which is used for caching images.
     */
    public static class LruBitmapCache extends LruCache<String, Bitmap>
            implements ImageLoader.ImageCache {

        public LruBitmapCache(int maxSize) {
            super(maxSize);
        }

        public LruBitmapCache(Context context) {
            this(getCacheSize(context));
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }

        private static int getCacheSize(Context ctx) {
            final DisplayMetrics displayMetrics = ctx.getResources().
                    getDisplayMetrics();
            final int screenWidth = displayMetrics.widthPixels;
            final int screenHeight = displayMetrics.heightPixels;
            // 4 bytes per pixel
            final int screenBytes = screenWidth * screenHeight * 4;

            return screenBytes * 3;
        }

    }



}
