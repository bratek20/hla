// DO NOT EDIT! Autogenerated by HLA tool

class SerializedDateRangeWrapper {
    private range = new SerializedDateRange

    toCustomType(): DateRangeWrapper {
        return SomeModule.CustomTypesMapper.dateRangeWrapperCreate(
            this.range.toCustomType(),
        )
    }

    static fromCustomType(customType: DateRangeWrapper): SerializedDateRangeWrapper {
        const instance = new SerializedDateRangeWrapper()
        instance.range = SerializedDateRange.fromCustomType(SomeModule.CustomTypesMapper.dateRangeWrapperGetRange(customType))
        return instance
    }
}