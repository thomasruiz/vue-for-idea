package io.j99.idea.vue.sdk;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModificationUtil;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Consumer;
import com.intellij.util.containers.HashMap;
import com.sun.javafx.collections.MappingChange;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by apple on 16/1/22.
 */
public class VueSdkUtils {
    public static boolean isVueModule(Module module){
        ModifiableRootModel modifiableRootModel =ModuleRootManager.getInstance(module).getModifiableModel();
        ContentEntry[] entris = modifiableRootModel.getContentEntries();
            for(ContentEntry ce:entris){
                VirtualFile file = ce.getFile();
                if(file==null)return false;
                if("package.json".equals(file.getName())){
                    try {
                        Map map=new Gson().fromJson(new FileReader(file.getPath()),HashMap.class);
                        if(map.containsKey("devDependencies")){
                            Map devDependencies = (Map) map.get("devDependencies");
                            if(devDependencies.containsKey("vue-loader")){
                                return true;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        return false;
    }
    public static void initVueSdkControls(Project project, TextFieldWithBrowseButton nodePathTextWithBrowse, TextFieldWithBrowseButton vuePathTextWithBrowse) {
        final TextComponentAccessor<JTextField> nodeComponentAccessor = new TextComponentAccessor<JTextField>() {
            @Override
            public String getText(final JTextField component) {
                String text = component.getText();
                if (StringUtil.isEmpty(text)) {
                    return SystemInfo.isWindows ? "C:\\Program Files\\nodejs" : SystemInfo.isMac ? "/usr/local/Cellar/node/bin/node" : "/usr/local/bin/node";
                }
                return text;
            }

            @Override
            public void setText(final JTextField component, @NotNull String text) {
                if (!text.isEmpty()) {
                    if(new File(text.trim()).isDirectory()) {
                        component.setText(FileUtilRt.toSystemDependentName(text+"/node"));
                    }else{
                        component.setText(FileUtilRt.toSystemDependentName(text));
                    }
                    return;
                }

                component.setText(FileUtilRt.toSystemDependentName(text));
            }
        };
        final TextComponentAccessor<JTextField> vueComponentAccessor = new TextComponentAccessor<JTextField>() {
            @Override
            public String getText(final JTextField component) {
                String text = component.getText();
                if (StringUtil.isEmpty(text)) {
                    return SystemInfo.isWindows ? "C:\\Program Files\\nodejs" : SystemInfo.isMac ? "/usr/local/Cellar/node/bin/vue" : "/usr/local/bin/vue";
                }
                return text;
            }

            @Override
            public void setText(final JTextField component, @NotNull String text) {
                if (!text.isEmpty()) {
                    if(new File(text.trim()).isDirectory()) {
                        component.setText(FileUtilRt.toSystemDependentName(text+"/vue"));
                    }else{
                        component.setText(FileUtilRt.toSystemDependentName(text));
                    }
                    return;
                }

                component.setText(FileUtilRt.toSystemDependentName(text));
            }
        };
        final ComponentWithBrowseButton.BrowseFolderActionListener<JTextField> browseNodeFolderListener =
                new ComponentWithBrowseButton.BrowseFolderActionListener<JTextField>("Select Node path", null, nodePathTextWithBrowse, project,
                        FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                        nodeComponentAccessor);
        final ComponentWithBrowseButton.BrowseFolderActionListener<JTextField> browseVueFolderListener =
                new ComponentWithBrowseButton.BrowseFolderActionListener<JTextField>("Select vue path", null, vuePathTextWithBrowse, project,
                        FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                        vueComponentAccessor);
        nodePathTextWithBrowse.addBrowseFolderListener(project, browseNodeFolderListener);
        vuePathTextWithBrowse.addBrowseFolderListener(project, browseVueFolderListener);
    }
}
