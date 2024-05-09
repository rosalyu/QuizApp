package com.example.quizapp
import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.shape.CornerFamily

class ListAdapter(
    private val mContext: Context,
    private val mResource: Int,
    private val mData: List<ListItemData>
    // TODO use bitmap object for the image files
) :
    ArrayAdapter<ListItemData?>(mContext, mResource, mData) {

    // called by the ListView when it needs to display the content of a list element at a certain position
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var listItemView = convertView
        // no free recycled views available (list empty or too full) -> inflate a new one
        if (listItemView == null) {
            val inflater = LayoutInflater.from(mContext)
            // represents the root view of a list element, which is a row of 2 items (ConstraintLayout)
            listItemView = inflater.inflate(mResource, parent, false)
        }
        // each quiz preview element in a row
        val firstItem = listItemView!!.findViewById<LinearLayout>(R.id.firstPreview) // not null assertion operator: !!
        val secondItem = listItemView.findViewById<LinearLayout>(R.id.secondPreview)

        // get the view elements of each quiz preview
        val tvFirstTitle = firstItem!!.findViewById<TextView>(R.id.tvTitle)
        val tvFirstDescription = firstItem.findViewById<TextView>(R.id.tvDescription)
        val tvFirstImage = firstItem.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.imageView)

        val tvSecondTitle = secondItem!!.findViewById<TextView>(R.id.tvTitle)
        val tvSecondDescription = secondItem.findViewById<TextView>(R.id.tvDescription)
        val tvSecondImage = secondItem.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.imageView)

        // get the current element from the data list, which contains the elements of for the ListView
        val currentItem: ListItemData = mData[position]

        // set data for the first quiz preview
        tvFirstTitle.text = currentItem.firstPreview.titleText
        tvFirstDescription.text = currentItem.firstPreview.descriptionText
        tvFirstImage.setImageResource(currentItem.firstPreview.imageResId)

        // set image corners
        // convert 10dp from xml to pixels to make the image corner radius equal to the
        // quiz preview corner radius
        val density = mContext.resources.displayMetrics.density
        val cornerRadiusPixels = ( GlobalVariables.cornerRadius * density + 0.5f)

        tvFirstImage.shapeAppearanceModel = tvFirstImage.shapeAppearanceModel.toBuilder()
            .apply { setBottomLeftCorner(CornerFamily.ROUNDED, cornerRadiusPixels);
                setBottomRightCorner(CornerFamily.ROUNDED, cornerRadiusPixels) }.build()

        // set data for the second quiz preview
        tvSecondTitle.text = currentItem.secondPreview.titleText
        tvSecondDescription.text = currentItem.secondPreview.descriptionText
        tvSecondImage.setImageResource(currentItem.secondPreview.imageResId)

        // set image corners
        tvSecondImage.shapeAppearanceModel = tvSecondImage.shapeAppearanceModel.toBuilder()
            .apply { setBottomLeftCorner(CornerFamily.ROUNDED, cornerRadiusPixels);
                setBottomRightCorner(CornerFamily.ROUNDED, cornerRadiusPixels) }.build()
        return listItemView
    }
}


