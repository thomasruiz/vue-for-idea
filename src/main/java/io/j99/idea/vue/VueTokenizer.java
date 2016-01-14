package io.j99.idea.vue;

import com.intellij.psi.tree.IElementType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by android on 1/14/16.
 */
public class VueTokenizer implements Tokenizer {
    private static Map<String,VueTokenType> kwd;
    static {
        kwd=new HashMap<>();
        kwd.put("template",VueTokenType.TAG_TEMPLATE);
        kwd.put("style",VueTokenType.TAG_STYLE);
        kwd.put("script",VueTokenType.TAG_SCRIPT);
    }
    @Override
    public int getTokenStart(int index) {
        return 0;
    }

    @Override
    public int getTokenEnd(int index) {
        return 0;
    }

    @Override
    public IElementType getTokenType(int index) {
        return null;
    }

    @Override
    public void resetSequence(CharSequence buffer) {

    }
}
