package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.mvvm.activity.mvvmActivityTemplate
import other.mvvm.fragment.mvvmFragmentTemplate

/**
 * @description:
 * @author:  stone
 * @email:
 * @date :   2022/2/8 下午5:48
 */
class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        // activity的模板
        mvvmActivityTemplate,
        // fragment的模板
        mvvmFragmentTemplate
    )
}