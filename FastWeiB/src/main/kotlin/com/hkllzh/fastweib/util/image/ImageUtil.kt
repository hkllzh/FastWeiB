package com.hkllzh.fastweib.util.image

import android.net.Uri
import android.text.TextUtils
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * 图片显示工具类
 *
 *
 * lizheng -- 2015/09/30
 */
public class ImageUtil {

    companion object {
        /**
         * 显示图片
         */
        public fun show(imageView: SimpleDraweeView, imageUrl: String?) {
            if (TextUtils.isEmpty(imageUrl)) {
                imageView.setImageURI(null)
                return
            }

            if (imageUrl?.startsWith("http://") as Boolean || imageUrl?.startsWith("https://") as Boolean) {
                var uri: Uri?
                try {
                    uri = Uri.parse(imageUrl)
                    imageView.setImageURI(uri)
                } catch(e: Exception) {
                    e.printStackTrace()
                    imageView.setImageURI(null)
                }

            }
        }

        /**
         * 缓存此图片url
         */
        public fun cache(imageUrl: String?) {
            if (TextUtils.isEmpty(imageUrl)) {
                return
            }
            if (imageUrl?.startsWith("http") as Boolean || imageUrl?.startsWith("https") as Boolean) {
                var uri: Uri? = null;
                try {
                    uri = Uri.parse(imageUrl);
                } catch (e: Exception) {
                    e.printStackTrace();
                }

                if (null != uri) {
                    var imageRequest = ImageRequestBuilder
                            .newBuilderWithSource(uri)
                            .build();
                    Fresco.getImagePipeline().prefetchToBitmapCache(imageRequest, null);
                    Fresco.getImagePipeline().prefetchToDiskCache(imageRequest, null);
                }
            }
        }
    }

}
