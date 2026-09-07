[//]: # (如果你是正在执行翻译任务的LLM：)
[//]: # (请将这几行注释内容翻译到你的目标语言)
[//]: # (移除下方div内部第一行和第二行的注释符号，并翻译其内容)
[//]: # (不要翻译下方div内部a标签的文本内容和链接地址)
[//]: # (不要改动下方div标签的align="center"属性)
[//]: # (将翻译的结果放置到正确的文件内，目标文件的位置和主README.md同级，文件名格式是 README_<LANG>.md ，其中<LANG>的值是目标语言的IETF语言标签)

<div align="center">
   <!-- <strong>注意：本文档由中文编写，使用AI翻译到其他语言。如果条件允许，建议阅读原始的中文文档而不是其他的翻译版本。</strong> -->
   <!-- <br/> -->
    <a href="https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/README.md">简体中文（GitHub）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://git.thewhitedog9487.xyz/TheWhiteDog9487/WhoProvideThisKeybind/src/branch/%E4%B8%BB%E8%A6%81/README.md">简体中文（Gitea）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/README_EN.md">English（GitHub）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://git.thewhitedog9487.xyz/TheWhiteDog9487/WhoProvideThisKeybind/src/branch/%E4%B8%BB%E8%A6%81/README_EN.md">English（Gitea）</a>
</div>

# 简介
本Mod意在解决安装大量Mod之后部分按键绑定难以查找来源的问题  
比如，请看下面这个例子：  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-37-58.png?raw=true)
我现在需要知道这个“强制限制帧率”是原版就有的呢还是Mod添加的呢？是哪个Mod添加的？  
好像没什么办法对吧？本Mod就是解决这个问题的  

# 功能
在更改绑定按键的按钮上显示悬浮文字，显示该按键由哪个Mod提供  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-06.png?raw=true)

默认配置下为了不对用户造成干扰，因此不显示对普通玩家没有意义的信息  
如果你需要的话，可以在配置页面里启用其他功能  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-14.png?raw=true)
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-32.png?raw=true)

# 重要提示
**本Mod无法保证结果100%准确！**  
像是CarryOn，AxolotlClient等少部分Mod注册绑定时不会提供自身信息或者提供的信息不准确，导致本Mod无法判断具体来源或者出现错判  
如果出现这种情况，请向对应Mod的开发者提交反馈，让其更正按键绑定ID到正确的格式  

# 兼容性
如果您使用的Mod替换或者大幅修改了原版Minecraft的按键绑定配置界面，那么就有可能出现兼容性问题  
如有遇到，请在[Github仓库](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/issues)开启一个issue，我会视具体情况尽力协助  

# 配置文件
- 如果你开启了版本隔离，它应当位于`.minecraft\versions\<版本名>\config\WhoProvideThisKeybind.json5`
- 如果没有版本隔离，它应该在`.minecraft\config\WhoProvideThisKeybind.json5`

这就是一个普通的json文本文件，只不过json5支持诸如注释等更多特性而已。  
所有的属性都有注释，包括功能、取值范围、类型、默认值等。  
使用普通的文本编辑器打开即可进行修改。  
你可以直接在游戏运行时编辑配置文件，保存之后会自动应用到游戏内。