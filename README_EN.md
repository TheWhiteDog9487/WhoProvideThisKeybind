<div align="center">
    <strong>Note: This document was originally written in Chinese and translated to other languages using AI. If possible, it is recommended to read the original Chinese document instead of other translations.</strong>
    <br/>
    <a href="https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/README.md">简体中文（GitHub）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://git.thewhitedog9487.xyz/TheWhiteDog9487/WhoProvideThisKeybind/src/branch/%E4%B8%BB%E8%A6%81/README.md">简体中文（Gitea）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/README_EN.md">English（GitHub）</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://git.thewhitedog9487.xyz/TheWhiteDog9487/WhoProvideThisKeybind/src/branch/%E4%B8%BB%E8%A6%81/README_EN.md">English（Gitea）</a>
</div>

# Introduction
This mod is designed to solve the problem of not knowing where some keybinds came from after installing many mods.  
For example, take a look at this case:  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-37-58.png?raw=true)
I need to know whether this “Force Limit Framerate” is part of the vanilla game or added by a mod, and if it was added by a mod, which one added it.  
It seems there is no obvious way to know, right? This mod is built to solve exactly that problem.  

# Features
Display hover text on the keybind modification button to show which mod provides that keybind.  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-06.png?raw=true)

By default, to avoid disturbing users, information that is not useful to ordinary players is not shown.  
If you need it, you can enable additional features in the config screen.  
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-14.png)
![](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/blob/%E4%B8%BB%E8%A6%81/%E5%9B%BE%E7%89%87/Snipaste_2026-09-03_20-38-32.png)

# Important Notes
**This mod cannot guarantee 100% accuracy!**  
Some mods, such as CarryOn, AxolotlClient, and a few others, do not provide their own information when registering keybinds or provide inaccurate information, which means this mod may be unable to determine the actual source or may make incorrect judgments.  
If this happens, please submit feedback to the corresponding mod developer and ask them to correct the keybind ID format to the proper standard.  

# Compatibility
If the mod you use replaces or heavily modifies the vanilla Minecraft keybind configuration interface, compatibility issues may occur.  
If you encounter this, please open an issue in the [GitHub repository](https://github.com/TheWhiteDog9487/WhoProvideThisKeybind/issues), and I will do my best to help based on the specific situation.  

# Configuration File
- If version isolation is enabled, it should be located at `.minecraft\versions\<version name>\config\WhoProvideThisKeybind.json5`
- If version isolation is not enabled, it should be located at `.minecraft\config\WhoProvideThisKeybind.json5`

This is just a normal JSON text file, except that JSON5 supports more features such as comments.  
All properties are documented, including features, value ranges, types, default values, and more.  
You can open it with a plain text editor and edit it directly.  
You can edit the configuration file while the game is running, and the changes will be applied to the game automatically after saving.
