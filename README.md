## 项目介绍

## 前端

### 技术选型及理由

前端一开始是想使用框架来进行的，但是感觉界面简洁一点会比较舒服，所以最后是只使用了原生的控件如google发布的Cardview和ConstraintLayout之类的来对主页面和各个功能页面进行布局。而且可以通过在布局编辑器当中拖动控件、添加约束来设计界面，十分方便。在 XML 中声明 UI 的优点在于，可以更好地将应用的外观与控制应用行为的代码隔离。当 UI 描述位于应用代码外部，这意味着在修改或调整描述时无需修改源代码并重新编译。

前端界面设计完成后使用了Gradle工具编译生成apk文件，这是一个基于Apache Ant和Apache Maven概念的项目自动化建构工具。在Gradle爆红之前，常用的构建工具是ANT，然后又进化到Maven。ANT和Maven这两个工具其实也还算方便，现在还有很多地方在使用。但是二者都有一些缺点，所以让更懒的人觉得不是那么方便。比如，Maven编译规则是用XML来编写的。XML虽然通俗易懂，但是很难在xml中描述if{某条件成立，编译某文件}/else{编译其他文件}这样有不同条件的任务。它使用一种基于Groovy的特定领域语言来声明项目设置，而不是传统的XML。当前其支持的语言限于Java、Groovy和Scala。选用Gradle是因为对于大部分的 Android 开发者来说 Gradle 是一个强大的工具，它提供便捷的方式帮助开发者构建 app。简单来说，Gradle就是工程的管理，帮做了依赖,打包,部署,发布,各种渠道的差异管理等工作。

采用了数据传输和事件监听响应系统，将前端UI和后端程序分成了视图层和逻辑层，当发生响应事件时，在主线程里新开线程处理网络请求，然后再跳回主线程进行UI更新。

### 架构设计

为了使页面简洁美观，逻辑合理。采用了传统的MVC架构，Controller即是**Activity**和**Fragment**，但是这两者掌握了Android系统中绝大多数的资源，并且在内部直接控制View。采用MVC模式的好处是便于UI界面部分的显示和业务逻辑，数据处理分开。

- M层：适合做一些业务逻辑处理，比如数据库存取操作，网络操作，复杂的算法，耗时的任务等都在model层处理。一般是也可以用来存储数据并显示在V层。
- V层：应用层中处理数据显示的部分，XML布局可以视为V层，显示Model层的数据结果。
- C层：在Android中，Activity处理用户交互问题，因此可以认为Activity是控制器，Controller即是Activity和Fragment，但是这两者掌握了Android系统中绝大多数的资源，并且在内部直接控制View，因此传统的Android App一般是以Activity和Fragment为核心，将网络模块，数据库管理模块，文件管理模块，常用工具类等分离成若干工具类包，供Activity和Fragment使用。

简单地说，这三者之间的关系就是

1. Model：模型层，负责处理数据的加载或者存储。

2. View：视图层，负责界面数据的展示，与用户进行交互
3. Controller：控制器层，负责逻辑业务的处理

![preview](https://pic4.zhimg.com/v2-c7ea8574c2a8d04c27acce3aa765b25e_r.jpg)

就以app里的功能进制转换为例，V层是进制转换功能的UI界面，是xml布局（详见activity_exchange.xml和arrays.xml）文件，而C层则是读取数据，与UI界面和用户交互的层面，对UI的输入进行读取，读取当前输入的数字后发起请求，然后再对数据进行转换（详见Exchange.java文档），最后将数据存储在M层里。

在V层中，使用XML文件来对UI界面进行基础布局，作为视图层，然后在C层中使用java文件来对从V层传输的数据进行多线程处理，网络请求，数据处理和交互都在C层中进行，最后把数据存储在M层中供V层访问。除此之外，事件监听等响应控件也是在C层进行处理，可以及时地对用户的数据进行更新。

### 模块划分

根据要提供的功能，将整个程序前端划分为了10个部分，分别是主页面和各个功能页面。当使用不同功能时，各个页面之间进行转换。除此之外，前端还建立了一些小的样式模块如button等供各个功能模块页面调用，为了文件的简洁，额外将各模块文件所需的文字排版单独存放在了一个文件夹。

文件结构如下：
```
res
    ├── drawable
    │   ├── add.png
    │   ├── arrow.png
    │   ├── back.jpeg
    │   ├── button.xml
    │   ├── circle.png
    │   ├── dian1.png
    │   ├── dian2.png
    │   ├── ic_launcher_background.xml
    │   ├── logo.jpeg
    │   ├── palette.png
    │   ├── search_button.xml
    │   └── white_button.xml
    ├── drawable-v24
    │   ├── ic_launcher_foreground.xml
    │   └── wheel.png
    ├── layout
    │   ├── activity_colorpick.xml
    │   ├── activity_exchange.xml
    │   ├── activity_home.xml
    │   ├── activity_logistics.xml
    │   ├── activity_newsshow.xml
    │   ├── activity_qrcode.xml
    │   ├── activity_random.xml
    │   ├── activity_south.xml
    │   ├── activity_timechange.xml
    │   ├── activity_transform.xml
    │   ├── activity_translate.xml
    │   ├── all_city.xml
    │   ├── listitem.xml
    │   └── time_card.xml
    ├── menu
    │   └── menu_main.xml
    ├── mipmap-anydpi-v26
    │   ├── ic_launcher.xml
    │   └── ic_launcher_round.xml
    ├── mipmap-hdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-mdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xxhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xxxhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── raw
    │   └── company
    └── values
        ├── arrays.xml
        ├── colors.xml
        ├── dimens.xml
        ├── strings.xml
        └── styles.xml
```
### 软件设计技术

#### 结构化程序设计

的以模块功能和处理过程设计为主，首先将app工具箱分为了9个功能子模块，各个模块单独编程，实现不同的功能，互不影响，各自独立。最后再由各模块连接，组合成完整的软件系统。使用的基本结构是选择结构，没有在程序中使用goto语句。

#### 面向对象程序设计

使用了Java进行编程，Java本身就是一种面向对象的语言。使用类进行封装，通过外部接口调用时不需要知道内部的具体实现方法，并且使代码模块化的同时增加了可读性和查错性。

#### 面向服务架构

Lite本身是被划分为app主模块和各服务子模块，这本身是一种面向服务的编程逻辑。将应用程序的不同功能单元（称为服务）进行拆分，并通过这些服务之间定义良好的接口和契约联系起来，这使得构建在各种各样的系统中的服务可以以一种统一和通用的方式进行交互。通过面向服务架构，根据需求通过网络对松散耦合的粗粒度应用组件进行分布式部署、组合和使用。

#### MVC架构

MVC架构是传统的Android APP架构，采用MVC架构来将数据和视图连接起来。前端利用XML文件来对视图进行设计，然后再利用C层次来对数据进行传输和处理，最后把处理后的数据存储在M层次。MVC架构的优点在于开发简单，以页面为导向，并且构建成功的话项目就已经基本实现模块化，使用MVC架构可以使理清代码各个部分所需要做的事情，并且有助于结构化编程。

## 后端

### 技术选型及理由

后端架构全部使用原生Java，不使用其它常用技术工具。理由如下：

本项目的最终程序全部运行在Android本地，项目实现的功能计算量本身不大，无需连接服务器，也无需维护数据库，因此没有使用数据库维护类、服务器维护类和服务器信息交流类技术工具的理由。

且该项目本身结构为完全树状，各模块的依赖关系接近0，文件结构等管理起来非常简单。此时使用其它附加工具增加工作效率不多，反而额外添加了学习成本。

### 架构设计和模块划分

项目总体使用树状架构，采取一个主页面，多个子模块的模式。一个子模块实现一个用户可使用的功能，各个子模块间相互独立，没有依赖关系，仅在该模块内完成全部工作。

子模块列表为:

- ExchangeRate：提供货币转换服务
- Logistics：提供物流查询服务
- Translate：提供翻译服务
- ColorPick：提供取色器服务
- QRcode：提供二维码生成服务
- WorldTime：提供时间显示和时区转换服务
- randomActivity：提供随机数生成服务
- southActivity：提供指南针服务
- transformActivity：提供数字进制转换服务

后端源码全部储存在/java/com/example/pineappleym/lite内。

以下为后端详细的项目结构。

```
├ lite
│   ├── Home.java
│   ├── Functions
│   │   ├── ExchangeRate
│   │   │   ├── Exchange.java
│   │   │   ├── Rate.java
│   │   │   └── RateData.java
│   │   ├── Logistics
│   │   │   ├── Express.java
│   │   │   ├── GetCompany.java
│   │   │   ├── GetContext.java
│   │   │   ├── GetImage.java
│   │   │   ├── Listitem.java
│   │   │   ├── Logistics.java
│   │   │   ├── NewsActivity.java
│   │   │   └── News_Adapter.java
│   │   └── Translate
│   │       ├── HttpGet.java
│   │       ├── MD5.java
│   │       ├── TransApi.java
│   │       ├── TransData.java
│   │       ├── TransResult.java
│   │       └── Translate.java
│   │   ├── ColorPick
│   │   │   └── ColorPick.java
│   │   ├── QRcode
│   │   │   ├── QRCodeUtil.java
│   │   │   └── QRcode.java
│   │   └── WorldTime
│   │       ├── City.java
│   │       ├── CityAdd.java
│   │       ├── Citybase.java
│   │       ├── MyOpenHelper.java
│   │       ├── TimeAdapter.java
│   │       ├── TimeThread.java
│   │       └── WorldTime.java
│   │   ├── randomActivity.java
│   │   ├── southActivity.java
│   │   └── transformActivity.java
```

### 软件设计技术

#### 结构化程序设计

基本结构上，本工程的功能选择通过主页面上的一个选择结构进行，所有子模块间完全独立。由于模块相互独立，因此在设计其中一个模块时，不会受到其它模块的牵连，因而可将原来较为复杂的问题化简为一系列简单模块的设计。

总体开发采取自上而下的模式，优先构思需要实现的功能，再逐步细化，具体分析该功能块的实现需要哪些小项功能。此外，作为手机应用，项目对模块的出入口和内部数据流进行了严格限制，禁止使用类似goto的语句。

#### MVC模式

本工程中用户的输入，输入内容的处理和最终结果的显示被分开处理，也即存在一个模型（Model）-视图（View）-控制器（Controller）的架构。

在使用过程中，用户输入由前端作为控制器进行处理。当用户提交输入时，创建一个子线程，将数据交由后端代码作为模型处理。当线程运行完毕后，把结果分发给主进程，由主进程更新窗口显示内容进行显示。

