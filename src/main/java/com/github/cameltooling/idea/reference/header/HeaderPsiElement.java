package com.github.cameltooling.idea.reference.header;

import com.github.cameltooling.idea.reference.FakeCamelPsiElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

public class HeaderPsiElement extends FakeCamelPsiElement {

    private final String headerName;

    public HeaderPsiElement(PsiElement element, String headerName) {
        super(element);
        this.headerName = headerName;
    }

    @Override
    public String getName() {
        return headerName;
    }

    @Override
    public @Nullable @Nls String getTypeName() {
        return "header";
    }
}
