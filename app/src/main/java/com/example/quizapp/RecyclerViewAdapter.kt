import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.GlobalVariables
import com.example.quizapp.ListItemData
import com.example.quizapp.R
import com.google.android.material.shape.CornerFamily

class RecyclerViewAdapter(private val data: List<ListItemData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var cornerRadiusPixels: Float = 0f

    // ViewHolder represents a single item view in the RecyclerView (not calling R-class each time is more efficient)
    // Instead of inflating a new layout every time a list item becomes visible,
    // RecyclerView reuses existing views by recycling ViewHolders
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //creation of ViewHolder causes layout inflation so the views inside of it become accessible
        // -> inflation only once for each view type (not for each item in the list like in ListView)
        // each quiz preview element in a row
        private val firstItem: LinearLayout = itemView.findViewById(R.id.firstPreview)
        private val secondItem: LinearLayout = itemView.findViewById(R.id.secondPreview)

        // get the view elements of each quiz preview
        val tvFirstTitle: TextView = firstItem.findViewById(R.id.tvTitle)
        val tvFirstDescription: TextView = firstItem.findViewById(R.id.tvDescription)
        val tvFirstImage: com.google.android.material.imageview.ShapeableImageView = firstItem.findViewById(R.id.imageView)

        val tvSecondTitle: TextView = secondItem!!.findViewById(R.id.tvTitle)
        val tvSecondDescription: TextView = secondItem.findViewById(R.id.tvDescription)
        val tvSecondImage: com.google.android.material.imageview.ShapeableImageView = secondItem.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // assign cornerRadiusPixels based on the parent context
        // convert 10dp from xml to pixels to make the image corner radius equal to the quiz preview corner radius
        val density: Float = parent.context.resources.displayMetrics.density
        cornerRadiusPixels = ( GlobalVariables.cornerRadius * density + 0.5f)

        // create ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tvFirstTitle.text = item.firstPreview.titleText
        holder.tvFirstDescription.text = item.firstPreview.descriptionText
        // todo image is not tv type
        holder.tvFirstImage.setImageResource(item.firstPreview.imageResId)

        // set the corner Radius to the first ShapeableImageView
        holder.tvFirstImage.shapeAppearanceModel = holder.tvFirstImage.shapeAppearanceModel.toBuilder()
            .apply { setBottomLeftCorner(CornerFamily.ROUNDED, cornerRadiusPixels);
                setBottomRightCorner(CornerFamily.ROUNDED, cornerRadiusPixels) }.build()

        holder.tvSecondTitle.text = item.secondPreview.titleText
        holder.tvSecondDescription.text = item.secondPreview.descriptionText
        // todo image is not tv type
        holder.tvSecondImage.setImageResource(item.secondPreview.imageResId)

        // set the corner Radius to the first ShapeableImageView
        holder.tvSecondImage.shapeAppearanceModel = holder.tvSecondImage.shapeAppearanceModel.toBuilder()
            .apply { setBottomLeftCorner(CornerFamily.ROUNDED, cornerRadiusPixels);
                setBottomRightCorner(CornerFamily.ROUNDED, cornerRadiusPixels) }.build()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
