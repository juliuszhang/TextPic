# TextPic 一个将图片转成字符组成的图片的应用（无聊写的）
## 原图
### ![avatar](https://github.com/juliuszhang/TextPic/blob/master/img/raw.JPG)
## 效果图
### ![avatar](https://github.com/juliuszhang/TextPic/blob/master/img/result.png)

# 如何使用
### 导入工程到idea 运行start.java 按照提示输入图片路径、输出路径和精度，精度默认为2

# 运行环境
## JDK1.8 
## maven(通过maven依赖了guava)

# 程序原理
## 通过逐行扫描图片的每一个像素（精度为2时扫描的步长为2 也就是扫描一个跳过一个，以此类推），获取到像素的RGB后根据RGB的颜色深浅转成不同的字符，并将字符输出到文本中
