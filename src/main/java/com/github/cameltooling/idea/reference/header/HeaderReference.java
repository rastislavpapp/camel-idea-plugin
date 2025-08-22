package com.github.cameltooling.idea.reference.header;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.searches.AllClassesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class HeaderReference extends PsiPolyVariantReferenceBase<PsiElement> {

    private final String header;

    public HeaderReference(@NotNull PsiElement psiElement, @NotNull String header) {
        super(psiElement, TextRange.from(getStartOffset(psiElement), header.length()));
        this.header = header;
    }

    private static int getStartOffset(PsiElement element) {
        return element.getText().startsWith("\"") ? 1 : 0;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        Module module = ModuleUtilCore.findModuleForPsiElement(myElement);
        if (module == null) {
            return ResolveResult.EMPTY_ARRAY;
        }

        List<PsiElement> results = new ArrayList<>();
        Collection<PsiClass> allClasses = AllClassesSearch.search(module.getModuleScope(), project).findAll();
        for (PsiClass aClass : allClasses) {
            Collection<PsiLiteralExpression> literals = PsiTreeUtil.findChildrenOfType(aClass, PsiLiteralExpression.class);
            for (PsiLiteralExpression literal : literals) {
                if (isHeaderRef(literal)) {
                    results.add(new HeaderPsiElement(literal, getHeader()));
                }
            }
        }
        return PsiElementResolveResult.createResults(results);
    }

    private boolean isHeaderRef(PsiLiteralExpression literal) {
        return Arrays.stream(literal.getReferences())
                .anyMatch(this::isHeaderRefElement);
    }

    protected abstract boolean isHeaderRefElement(PsiReference ref);

}
