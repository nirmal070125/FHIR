(window.webpackJsonp=window.webpackJsonp||[]).push([[22,17],{"013z":function(e,t,a){"use strict";a("HQhv"),a("sC2a");var r=a("qKvR"),n=a("pOBw"),o=a("q1tI"),c=a.n(o),i=a("huSw"),l=a("t0dz"),s=a.n(l),b=a("Bfmn"),u=a("a7k6"),d=a("TSYQ"),p=a.n(d),m=a("QH2O"),f=function(e){var t,a=e.children,n=e.title,o=e.tabs,c=void 0===o?[]:o,i=e.shouldHideHeader;return Object(r.b)("div",{className:p()((t={},t[m.pageHeader]=m.pageHeader,t[m.pageHeaderSticky]=c.length,t[m.pageHeaderShifted]=i,t))},Object(r.b)("div",{className:"bx--grid"},Object(r.b)("div",{className:"bx--row"},Object(r.b)("div",{className:"bx--col-lg-12"},Object(r.b)("h1",{id:"page-title",className:m.text},n)))),a)},x=a("Z3H1"),g=a("BAC9"),O=function(e){var t=e.relativePagePath,a=e.repository,n=x.data.site.siteMetadata.repository,o=a||n,c=o.baseUrl,i=c+"/tree/master"+o.subDirectory+"/src/pages"+t;return c?Object(r.b)("div",{className:"bx--row "+g.row},Object(r.b)("div",{className:"bx--col"},Object(r.b)("a",{className:g.link,href:i},"Edit this page on GitHub"))):null},h=a("FCXl"),j=(a("q8oJ"),a("8npG"),a("nWfQ"),a("Wbzz")),v=a("I8xM");var H=function(e){var t,a;a=e,(t=n).prototype=Object.create(a.prototype),t.prototype.constructor=t,t.__proto__=a;!function(e){function t(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}}(n);function n(){return e.apply(this,arguments)||this}return n.prototype.render=function(){var e=this.props,t=e.tabs,a=e.slug,n=a.split("/").filter(Boolean).slice(-1)[0],o=t.map((function(e){var t,o=s()(e,{lower:!0}),c=o===n,i=a.replace(n,o);return Object(r.b)("li",{key:e,className:p()((t={},t[v.selectedItem]=c,t),v.listItem)},Object(r.b)(j.Link,{className:v.link,to:""+i},e))}));return Object(r.b)("div",{className:v.tabsContainer},Object(r.b)("div",{className:"bx--grid"},Object(r.b)("div",{className:"bx--row"},Object(r.b)("div",{className:"bx--col-lg-12 bx--col-no-gutter"},Object(r.b)("nav",null,Object(r.b)("ul",{className:v.list},o))))))},n}(c.a.Component),y=a("MjG9");t.a=function(e){var t=e.pageContext,a=e.children,o=e.location,c=t.frontmatter,l=void 0===c?{}:c,d=t.relativePagePath,p=l.tabs,m=l.title,x=Object(b.c)(),g=!!p&&"down"===x,j=n.data.site.pathPrefix,v=j?o.pathname.replace(j,""):o.pathname,P=p?v.split("/").slice(-1)[0]||s()(p[0],{lower:!0}):"";return Object(r.b)(u.a,{shouldHideHeader:g,homepage:!1},Object(r.b)(f,{shouldHideHeader:g,title:m,label:"label",tabs:p},p&&Object(r.b)(H,{slug:v,tabs:p,currentTab:P})),Object(r.b)(y.a,{padded:!0},a,Object(r.b)(O,{relativePagePath:d})),Object(r.b)(h.a,{pageContext:t,location:o,slug:v,tabs:p,currentTab:P}),Object(r.b)(i.a,null))}},BAC9:function(e,t,a){e.exports={bxTextTruncateEnd:"EditLink-module--bx--text-truncate--end--2pqje",bxTextTruncateFront:"EditLink-module--bx--text-truncate--front--3_lIE",link:"EditLink-module--link--1qzW3",row:"EditLink-module--row--1B9Gk"}},I8xM:function(e,t,a){e.exports={bxTextTruncateEnd:"PageTabs-module--bx--text-truncate--end--267NA",bxTextTruncateFront:"PageTabs-module--bx--text-truncate--front--3xEQF",tabsContainer:"PageTabs-module--tabs-container--8N4k0",list:"PageTabs-module--list--3eFQc",listItem:"PageTabs-module--list-item--nUmtD",link:"PageTabs-module--link--1mDJ1",selectedItem:"PageTabs-module--selected-item--YPVr3"}},QH2O:function(e,t,a){e.exports={bxTextTruncateEnd:"PageHeader-module--bx--text-truncate--end--mZWeX",bxTextTruncateFront:"PageHeader-module--bx--text-truncate--front--3zvrI",pageHeader:"PageHeader-module--page-header--3hIan",text:"PageHeader-module--text--o9LFq",pageHeaderSticky:"PageHeader-module--page-header--sticky--1ElAE",pageHeaderShifted:"PageHeader-module--page-header--shifted--1sRXO"}},R583:function(e,t,a){"use strict";a.r(t),a.d(t,"_frontmatter",(function(){return c})),a.d(t,"default",(function(){return s}));a("E5k/"),a("rzGZ"),a("Dq+y"),a("8npG"),a("Ggvi"),a("qKvR"),a("q1tI");var r=a("E/Ix"),n=a("013z");function o(){return(o=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r])}return e}).apply(this,arguments)}var c={},i={_frontmatter:c},l=n.a;function s(e){var t=e.components,a=function(e,t){if(null==e)return{};var a,r,n={},o=Object.keys(e);for(r=0;r<o.length;r++)a=o[r],t.indexOf(a)>=0||(n[a]=e[a]);return n}(e,["components"]);return Object(r.b)(l,o({},i,a,{components:t,mdxType:"MDXLayout"}),Object(r.b)("h1",{id:"release-number"},"Release Number"),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},"0.0.1"),Object(r.b)("li",{parentName:"ul"},"JDK: ")),Object(r.b)("p",null,"Link to Download on GitHub releases"),Object(r.b)("p",null,"Maven Dependencies "),Object(r.b)("h1",{id:"change-log"},"Change Log"))}s.isMDXComponent=!0},Z3H1:function(e){e.exports=JSON.parse('{"data":{"site":{"siteMetadata":{"repository":{"baseUrl":"https://github.com/IBM/FHIR","subDirectory":"/docs"}}}}}')},pOBw:function(e){e.exports=JSON.parse('{"data":{"site":{"pathPrefix":"/FHIR"}}}')}}]);
//# sourceMappingURL=component---src-pages-releases-4-0-0-md-edccf220c89963434a97.js.map