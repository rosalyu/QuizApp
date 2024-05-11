import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.GlobalVariables
import com.example.quizapp.QuizPreviewData
import com.example.quizapp.R
import com.google.android.material.shape.CornerFamily

class RecyclerViewAdapter(private val data: List<QuizPreviewData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var cornerRadiusPixels: Float = 0f

    // ViewHolder represents a single item view in the RecyclerView (not calling R-class each time is more efficient)
    // Instead of inflating a new layout every time a list item becomes visible,
    // RecyclerView reuses existing views by recycling ViewHolders
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //creation of ViewHolder causes layout inflation so the views inside of it become accessible
        // -> inflation only once for each view type (not for each item in the list like in ListView)

        // get the view elements of each quiz preview
        val imageView: com.google.android.material.imageview.ShapeableImageView = itemView.findViewById(R.id.imageView)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // todo is calculated for each quiz preview, make sure it is only once in the class but get a context
        // assign cornerRadiusPixels based on the parent context
        // convert 10dp from xml to pixels to make the image corner radius equal to the quiz preview corner radius
        val density: Float = parent.context.resources.displayMetrics.density
        cornerRadiusPixels = ( GlobalVariables.cornerRadius * density + 0.5f)

        // create ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_preview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.tvTitle.text = item.titleText
        holder.tvDescription.text = item.descriptionText

        // set the corner Radius to the first ShapeableImageView
        holder.imageView.shapeAppearanceModel = holder.imageView.shapeAppearanceModel.toBuilder()
            .apply { setBottomLeftCorner(CornerFamily.ROUNDED, cornerRadiusPixels); setBottomRightCorner(CornerFamily.ROUNDED, cornerRadiusPixels) }.build()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
