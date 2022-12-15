package com.bookappointmentcalender.databinding;
import com.bookappointmentcalender.R;
import com.bookappointmentcalender.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDashboardBindingImpl extends ActivityDashboardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_dashboard", "toolbar_menu"},
            new int[] {1, 2},
            new int[] {com.bookappointmentcalender.R.layout.toolbar_dashboard,
                com.bookappointmentcalender.R.layout.toolbar_menu});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.frame_dashboard, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDashboardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ActivityDashboardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.FrameLayout) bindings[3]
            , (com.bookappointmentcalender.databinding.ToolbarDashboardBinding) bindings[1]
            , (com.bookappointmentcalender.databinding.ToolbarMenuBinding) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setContainedBinding(this.toolbarDashboard);
        setContainedBinding(this.toolbarMenu);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        toolbarDashboard.invalidateAll();
        toolbarMenu.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (toolbarDashboard.hasPendingBindings()) {
            return true;
        }
        if (toolbarMenu.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbarDashboard.setLifecycleOwner(lifecycleOwner);
        toolbarMenu.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeToolbarDashboard((com.bookappointmentcalender.databinding.ToolbarDashboardBinding) object, fieldId);
            case 1 :
                return onChangeToolbarMenu((com.bookappointmentcalender.databinding.ToolbarMenuBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeToolbarDashboard(com.bookappointmentcalender.databinding.ToolbarDashboardBinding ToolbarDashboard, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeToolbarMenu(com.bookappointmentcalender.databinding.ToolbarMenuBinding ToolbarMenu, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(toolbarDashboard);
        executeBindingsOn(toolbarMenu);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): toolbarDashboard
        flag 1 (0x2L): toolbarMenu
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}