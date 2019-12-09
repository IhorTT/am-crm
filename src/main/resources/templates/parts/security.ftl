<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    userName = user.getUsername()
    isAdmin= user.isAdmin()
    isOwnObjects = user.isOwnObjects()
    isViewOnly = user.isViewOnly()
    >
<#else>
    <#assign
    userName = ""
    isAdmin = false
    isOwnObjects = true
    isViewOnly = true
    >
</#if>