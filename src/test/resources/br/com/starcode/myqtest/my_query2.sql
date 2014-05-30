select * from ${table!"tab1"}
<#if cond??>
where field = ${cond}
</#if>