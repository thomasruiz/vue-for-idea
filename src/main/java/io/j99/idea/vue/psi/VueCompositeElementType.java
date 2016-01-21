package io.j99.idea.vue.psi;

import com.intellij.psi.tree.IElementType;
import io.j99.idea.vue.VueLanguage;

/**
 * Created by apple on 16/1/21.
 */
public class VueCompositeElementType extends IElementType {
    public VueCompositeElementType(String debug){
        super(debug, new VueLanguage());
    }
}
