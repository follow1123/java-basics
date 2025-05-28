module nine.feat.moduleb {
    // 暴露包
    exports org.example.mapper;
    // 引入模块a，并自动引入模块a所引入的模块
    requires transitive nine.feat.modulea;
}