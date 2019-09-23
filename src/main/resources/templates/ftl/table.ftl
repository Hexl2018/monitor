<#--
currentChange 页码变更回调
sizeChange 每页条数变更回调
selectionChange 列表多选
-->

<#macro table data currentChange sizeChange selectionChange="" sortChange="" defaultSort="" height="" key="" paginationType=''>
    <el-table :data="${data}.records"
              <#if height!="">:height="${height}"</#if>
              style="width: 100%" stripe highlight-current-row="true"
              <#if defaultSort!="">:default-sort="${defaultSort}"</#if>
              <#if sortChange!="">@sort-change="${sortChange}"</#if>
              <#if selectionChange!="">@selection-change="${selectionChange}" </#if>
              <#if key!="">key="${key}"</#if>>
        <#nested/>
    </el-table>
    <div class="block" style="padding-top:6px;">
        <#if paginationType!="">
            <el-pagination @size-change="${sizeChange}" @current-change="${currentChange}"
                           :current-page="${data}.current" background
                           small
                           :page-sizes="[10,20,30,50,100]"
                           :page-size="${data}.size"
                           layout="total, sizes, prev, pager, next"
                           :total="${data}.total">
            </el-pagination>
        <#else>
            <el-pagination @size-change="${sizeChange}" @current-change="${currentChange}"
                           :current-page="${data}.current" background
                           :page-sizes="[10,20,30,50,100]"
                           :page-size="${data}.size"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="${data}.total">
            </el-pagination>
        </#if>
    </div>
</#macro>

<#macro column type="" name="" title="" width="" align="center" fixed="" sortable="">
<#if type=="selection">
    <el-table-column type="selection" show-overflow-tooltip="true" <#if width!=""> min-width="${width}"</#if> align="${align}"></el-table-column>
<#elseif type=="oper">
<el-table-column <#if fixed!="">fixed="${fixed}"</#if> show-overflow-tooltip="true" label="${title}" align="${align}" <#if width!=""> width="${width}"</#if>>
    <#nested/>
</el-table-column>
<#else>
<el-table-column <#if fixed!="">fixed="${fixed}"</#if> show-overflow-tooltip="true" prop="${name}" label="${title}" align="${align}" <#if width!=""> min-width="${width}"</#if> <#if sortable=="true">sortable="custom"</#if>><#nested/></el-table-column>
</#if>
</#macro>