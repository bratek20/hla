// DO NOT EDIT! Autogenerated by HLA tool

class SomeData {
    other = new OtherData
    custom = ANY
    private customOpt? = ANY

    static create(
        other: OtherData,
        custom: any,
        customOpt: Optional<any>,
    ): SomeData {
        const instance = new SomeData()
        instance.other = other
        instance.custom = custom
        instance.customOpt = customOpt.orElse(undefined)
        return instance
    }

    getCustomOpt(): Optional<any> {
        return Optional.of(this.customOpt)
    }
}