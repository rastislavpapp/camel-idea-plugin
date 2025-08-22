package com.github.cameltooling.idea.reference.header;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public class SetHeaderReference extends HeaderReference {

    public SetHeaderReference(@NotNull PsiElement psiElement, @NotNull String header) {
        super(psiElement, header);
    }

    @Override
    protected boolean isHeaderRefElement(PsiReference ref) {
        if (ref instanceof HeaderReference hr) {
            return hr.getHeader().equals(getHeader());
        }
        return false;
    }

}
