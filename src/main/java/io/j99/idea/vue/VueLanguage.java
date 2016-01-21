package io.j99.idea.vue;

import com.intellij.lang.Language;

/**
 * Created by android on 1/14/16.
 */
public class VueLanguage extends Language {
    public VueLanguage(){
        super("Vue","text/vue");
//        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
//            @NotNull
//            @Override
//            protected SyntaxHighlighter createHighlighter() {
//                return new VueSyntaxHighligher();
//            }
//        });
    }
}
