package com.santisteban.mario.reportalo_ya.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.santisteban.mario.reportalo_ya.R

class AboutFragment : Fragment() {

    private lateinit var firstParagraphTextView: TextView
    private lateinit var secondParagraphTextView: TextView
    private lateinit var thirdParagraphTextView: TextView

    private var isExpandedFirstParagraph = false
    private var isExpandedSecondParagraph = false
    private var isExpandedThirdParagraph = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        firstParagraphTextView = view.findViewById(R.id.first_paragraph_textview)
        secondParagraphTextView = view.findViewById(R.id.second_paragraph_textview)
        thirdParagraphTextView = view.findViewById(R.id.third_paragraph_textview)

        firstParagraphTextView.setOnClickListener {
            isExpandedFirstParagraph = !isExpandedFirstParagraph
            toggleExpansion(firstParagraphTextView, isExpandedFirstParagraph)
        }

        secondParagraphTextView.setOnClickListener {
            isExpandedSecondParagraph = !isExpandedSecondParagraph
            toggleExpansion(secondParagraphTextView, isExpandedSecondParagraph)
        }

        thirdParagraphTextView.setOnClickListener {
            isExpandedThirdParagraph = !isExpandedThirdParagraph
            toggleExpansion(thirdParagraphTextView, isExpandedThirdParagraph)
        }

        return view
    }

    private fun toggleExpansion(textView: TextView, isExpanded: Boolean) {
        if (isExpanded) {
            textView.maxLines = Integer.MAX_VALUE
        } else {
            textView.maxLines = 2
        }
    }
}
