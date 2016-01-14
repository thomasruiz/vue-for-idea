package io.j99.idea.vue.lexer;

import com.intellij.psi.tree.IElementType;
import io.j99.idea.vue.VueFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class VueElementType extends IElementType {
    public VueElementType(@NotNull @NonNls String debugName) {
        super(debugName, VueFileType.INSTANCE.getLanguage());
    }

}
