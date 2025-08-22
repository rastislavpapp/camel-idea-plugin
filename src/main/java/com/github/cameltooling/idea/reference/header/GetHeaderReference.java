package com.github.cameltooling.idea.reference.header;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;

public class GetHeaderReference extends HeaderReference {

    public GetHeaderReference(@NotNull PsiElement psiElement, @NotNull String header) {
        super(psiElement, header);
    }

    @Override
    protected boolean isHeaderRefElement(PsiReference ref) {
        if (ref instanceof SetHeaderReference hr) {
            return hr.getHeader().equals(getHeader());
        }
        return false;
    }

}
