package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public interface WebRetriever {

   class MarkUp {
       public static final String BODY_MARK_UP = "body";
       public static final String HEAD_MARK_UP = "head";

       private MarkUp() {

       }
   }


    Map<LineNumber, Line> mappingBody();

    Map<LineNumber, Line> mappingHead();
}
