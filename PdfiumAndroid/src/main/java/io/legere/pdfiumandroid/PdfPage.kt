@file:Suppress("unused")

package io.legere.pdfiumandroid

import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import android.view.Surface
import io.legere.pdfiumandroid.util.Size
import java.io.Closeable

@Suppress("TooManyFunctions")
class PdfPage(
    private val doc: PdfDocument,
    private val pageIndex: Int,
    val pagePtr: Long,
    private val mCurrentDpi: Int
) : Closeable {

    private external fun nativeClosePage(pagePtr: Long)
    private external fun nativeClosePages(pagesPtr: LongArray)
    private external fun nativeGetPageWidthPixel(pagePtr: Long, dpi: Int): Int
    private external fun nativeGetPageHeightPixel(pagePtr: Long, dpi: Int): Int
    private external fun nativeGetPageWidthPoint(pagePtr: Long): Int
    private external fun nativeGetPageHeightPoint(pagePtr: Long): Int
    private external fun nativeGetFontSize(pagePtr: Long, charIndex: Int): Double
    private external fun nativeGetPageMediaBox(pagePtr: Long): FloatArray
    private external fun nativeGetPageCropBox(pagePtr: Long): FloatArray
    private external fun nativeGetPageBleedBox(pagePtr: Long): FloatArray
    private external fun nativeGetPageTrimBox(pagePtr: Long): FloatArray
    private external fun nativeGetPageArtBox(pagePtr: Long): FloatArray
    private external fun nativeGetPageBoundingBox(pagePtr: Long): FloatArray
    private external fun nativeGetDestPageIndex(docPtr: Long, linkPtr: Long): Int?
    private external fun nativeGetLinkURI(docPtr: Long, linkPtr: Long): String?
    private external fun nativeGetLinkRect(docPtr: Long, linkPtr: Long): RectF?
    @Suppress("LongParameterList")
    private external fun nativeRenderPage(
        pagePtr: Long, surface: Surface?, dpi: Int,
        startX: Int, startY: Int,
        drawSizeHor: Int, drawSizeVer: Int,
        renderAnnot: Boolean
    )

    @Suppress("LongParameterList")
    private external fun nativeRenderPageBitmap(
        pagePtr: Long, bitmap: Bitmap?, dpi: Int,
        startX: Int, startY: Int,
        drawSizeHor: Int, drawSizeVer: Int,
        renderAnnot: Boolean, textMask: Boolean
    )
    private external fun nativeGetPageSizeByIndex(docPtr: Long, pageIndex: Int, dpi: Int): Size
    private external fun nativeGetPageLinks(pagePtr: Long): LongArray

    @Suppress("LongParameterList")
    private external fun nativePageCoordsToDevice(
        pagePtr: Long, startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, pageX: Double, pageY: Double
    ): Point

    @Suppress("LongParameterList")
    private external fun nativeDeviceCoordsToPage(
        pagePtr: Long, startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, deviceX: Int, deviceY: Int
    ): PointF

    /**
     * Get page width in pixels. <br></br>
     * This method requires page to be opened.
     */
    fun getPageWidth(): Int {
        synchronized(PdfiumCore.lock) {
            return nativeGetPageWidthPixel(pagePtr, mCurrentDpi)
        }
    }

    /**
     * Get page height in pixels. <br></br>
     * This method requires page to be opened.
     */
    fun getPageHeight(): Int {
        synchronized(PdfiumCore.lock) {
            return nativeGetPageHeightPixel(pagePtr, mCurrentDpi)
        }
    }

    /**
     * Get page width in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageWidthPoint(): Int {
        synchronized(PdfiumCore.lock) {
            return nativeGetPageWidthPoint(pagePtr)
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageHeightPoint(): Int {
        synchronized(PdfiumCore.lock) {
            return nativeGetPageHeightPoint(pagePtr)
        }
    }

    /**
     * Get character font size in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getFontSize(charIndex: Int): Double {
        synchronized(PdfiumCore.lock) {
            return nativeGetFontSize(pagePtr, charIndex)
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageCropBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageCropBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageMediaBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageMediaBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageBleedBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageBleedBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageTrimBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageTrimBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageArtBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageArtBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get page height in PostScript points (1/72th of an inch).<br></br>
     * This method requires page to be opened.
     */
    fun getPageBoundingBox(): RectF {
        synchronized(PdfiumCore.lock) {
            val o = nativeGetPageBoundingBox(pagePtr)
            val r = RectF()
            r.left = o[LEFT]
            r.top = o[TOP]
            r.right = o[RIGHT]
            r.bottom = o[BOTTOM]
            return r
        }
    }

    /**
     * Get size of page in pixels.<br></br>
     * This method does not require given page to be opened.
     */
    fun getPageSize(): Size {
        synchronized(PdfiumCore.lock) {
            return nativeGetPageSizeByIndex(
                doc.mNativeDocPtr,
                pageIndex,
                mCurrentDpi
            )
        }
    }

    /**
     * Render page fragment on [Surface].<br></br>
     * Page must be opened before rendering.
     */
    fun renderPage(surface: Surface?,
        startX: Int, startY: Int, drawSizeX: Int, drawSizeY: Int
    ) {
        renderPage(surface, startX, startY, drawSizeX, drawSizeY, false)
    }

    /**
     * Render page fragment on [Surface]. This method allows to render annotations.<br></br>
     * Page must be opened before rendering.
     */
    @Suppress("LongParameterList", "TooGenericExceptionCaught")
    fun renderPage(
        surface: Surface?,
        startX: Int, startY: Int, drawSizeX: Int, drawSizeY: Int,
        renderAnnot: Boolean
    ) {
        synchronized(PdfiumCore.lock) {
            try {
                //nativeRenderPage(doc.mNativePagesPtr.get(pageIndex), surface, mCurrentDpi);
                nativeRenderPage(
                    pagePtr, surface, mCurrentDpi,
                    startX, startY, drawSizeX, drawSizeY, renderAnnot
                )
            } catch (e: NullPointerException) {
                Log.e(TAG, "mContext may be null", e)
            } catch (e: Exception) {
                Log.e(TAG, "Exception throw from native", e)
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    fun textPageGetFontSize(index: Int): Double {
        synchronized(PdfiumCore.lock) {
            try {
                return nativeGetFontSize(pagePtr, index)
            } catch (e: NullPointerException) {
                Log.e(TAG, "mContext may be null")
                e.printStackTrace()
            } catch (e: Exception) {
                Log.e(TAG, "Exception throw from native")
                e.printStackTrace()
            }
        }
        return 0.0
    }

    /**
     * Render page fragment on [Bitmap].<br></br>
     * Page must be opened before rendering.
     *
     *
     * Supported bitmap configurations:
     *
     *  * ARGB_8888 - best quality, high memory usage, higher possibility of OutOfMemoryError
     *  * RGB_565 - little worse quality, twice less memory usage
     *
     */
    @Suppress("LongParameterList")
    fun renderPageBitmap(bitmap: Bitmap?,
        startX: Int, startY: Int, drawSizeX: Int, drawSizeY: Int, textMask: Boolean
    ) {
        renderPageBitmap(
            bitmap,
            startX,
            startY,
            drawSizeX,
            drawSizeY,
            false,
            textMask
        )
    }

    /**
     * Render page fragment on [Bitmap]. This method allows to render annotations.<br></br>
     * Page must be opened before rendering.
     *
     *
     * For more info see [PdfiumCore.renderPageBitmap]
     */
    @Suppress("LongParameterList")
    fun renderPageBitmap(bitmap: Bitmap?,
        startX: Int, startY: Int, drawSizeX: Int, drawSizeY: Int,
        renderAnnot: Boolean, textMask: Boolean
    ) {
        synchronized(PdfiumCore.lock) {
            nativeRenderPageBitmap(
                pagePtr, bitmap, mCurrentDpi,
                startX, startY, drawSizeX, drawSizeY, renderAnnot, textMask
            )
        }
    }

    /** Get all links from given page  */
    fun getPageLinks(): List<PdfDocument.Link> {
        synchronized(PdfiumCore.lock) {
            val links: MutableList<PdfDocument.Link> =
                ArrayList()
            val linkPtrs = nativeGetPageLinks(pagePtr)
            for (linkPtr in linkPtrs) {
                val index = nativeGetDestPageIndex(doc.mNativeDocPtr, linkPtr)
                val uri = nativeGetLinkURI(doc.mNativeDocPtr, linkPtr)
                val rect = nativeGetLinkRect(doc.mNativeDocPtr, linkPtr)
                if (rect != null && (index != null || uri != null)) {
                    links.add(PdfDocument.Link(rect, index, uri))
                }
            }
            return links
        }
    }

    /**
     * Map page coordinates to device screen coordinates
     *
     * @param startX    left pixel position of the display area in device coordinates
     * @param startY    top pixel position of the display area in device coordinates
     * @param sizeX     horizontal size (in pixels) for displaying the page
     * @param sizeY     vertical size (in pixels) for displaying the page
     * @param rotate    page orientation: 0 (normal), 1 (rotated 90 degrees clockwise),
     * 2 (rotated 180 degrees), 3 (rotated 90 degrees counter-clockwise)
     * @param pageX     X value in page coordinates
     * @param pageY     Y value in page coordinate
     * @return mapped coordinates
     */
    @Suppress("LongParameterList")
    fun mapPageCoordsToDevice(startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, pageX: Double, pageY: Double
    ): Point {
        return nativePageCoordsToDevice(pagePtr, startX, startY, sizeX, sizeY, rotate, pageX, pageY)
    }

    /**
     * Map device screen coordinates to page coordinates
     *
     * @param startX    left pixel position of the display area in device coordinates
     * @param startY    top pixel position of the display area in device coordinates
     * @param sizeX     horizontal size (in pixels) for displaying the page
     * @param sizeY     vertical size (in pixels) for displaying the page
     * @param rotate    page orientation: 0 (normal), 1 (rotated 90 degrees clockwise),
     * 2 (rotated 180 degrees), 3 (rotated 90 degrees counter-clockwise)
     * @param deviceX   X value in page coordinates
     * @param deviceY   Y value in page coordinate
     * @return mapped coordinates
     */
    @Suppress("LongParameterList")
    fun mapDeviceCoordsToPage(startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, deviceX: Int, deviceY: Int
    ): PointF {
        return nativeDeviceCoordsToPage(
            pagePtr,
            startX,
            startY,
            sizeX,
            sizeY,
            rotate,
            deviceX,
            deviceY
        )
    }

    /**
     * @see PdfiumCore.mapPageCoordsToDevice
     * @return mapped coordinates
     */
    @Suppress("LongParameterList")
    fun mapRectToDevice(startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, coords: RectF
    ): RectF {
        val leftTop = mapPageCoordsToDevice(startX, startY, sizeX, sizeY, rotate,
            coords.left.toDouble(), coords.top.toDouble()
        )
        val rightBottom = mapPageCoordsToDevice(startX, startY, sizeX, sizeY, rotate,
            coords.right.toDouble(), coords.bottom.toDouble()
        )
        return RectF(
            leftTop.x.toFloat(),
            leftTop.y.toFloat(),
            rightBottom.x.toFloat(),
            rightBottom.y.toFloat()
        )
    }

    /**
     * @see PdfiumCore.mapDeviceCoordsToPage
     * @return mapped coordinates
     */
    @Suppress("LongParameterList")
    fun mapRectToPage(startX: Int, startY: Int, sizeX: Int,
        sizeY: Int, rotate: Int, coords: RectF
    ): RectF {
        val leftTop = mapDeviceCoordsToPage(
            startX,
            startY,
            sizeX,
            sizeY,
            rotate,
            coords.left.toInt(),
            coords.top.toInt()
        )
        val rightBottom = mapDeviceCoordsToPage(
            startX,
            startY,
            sizeX,
            sizeY,
            rotate,
            coords.right.toInt(),
            coords.bottom.toInt()
        )
        return RectF(leftTop.x, leftTop.y, rightBottom.x, rightBottom.y)
    }



    override fun close() {
        synchronized(PdfiumCore.lock) {
            nativeClosePage(pagePtr)
        }
    }

    companion object {
        private const val TAG =  "PdfPage"

        const val LEFT = 0
        const val TOP = 1
        const val RIGHT = 2
        const val BOTTOM = 3
    }

}