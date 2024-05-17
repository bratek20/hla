namespace SomeModule.Builder {
    export interface DateRangeWrapperDef {
        range?: TypesModule.Builder.DateRangeDef,
    }
    export function dateRangeWrapper(def?: DateRangeWrapperDef): DateRangeWrapper {
        const range = def?.range ?? {}

        return CustomTypesMapper.dateRangeWrapperCreate(
            TypesModule.Builder.dateRange(range),
        )
    }

    export interface SomePropertyDef {
        other?: OtherModule.Builder.OtherPropertyDef,
    }
    export function someProperty(def?: SomePropertyDef): SomeProperty {
        const other = def?.other ?? {}

        return SomeProperty.create(
            OtherModule.Builder.otherProperty(other),
        )
    }

    export interface SomeClassDef {
        id?: string,
        amount?: number,
    }
    export function someClass(def?: SomeClassDef): SomeClass {
        const id = def?.id ?? "someValue"
        const amount = def?.amount ?? 0

        return new SomeClass(
            new SomeId(id),
            amount,
        )
    }

    export interface SomeClass2Def {
        id?: string,
        enabled?: boolean,
        names?: string[],
        ids?: string[],
    }
    export function someClass2(def?: SomeClass2Def): SomeClass2 {
        const id = def?.id ?? "someValue"
        const enabled = def?.enabled ?? false
        const names = def?.names ?? []
        const ids = def?.ids ?? []

        return new SomeClass2(
            new SomeId(id),
            enabled,
            names,
            ids.map(it => new SomeId(it)),
        )
    }

    export interface SomeClass3Def {
        class2Object?: SomeClass2Def,
        class2List?: SomeClass2Def[],
        someEnum?: SomeEnum,
    }
    export function someClass3(def?: SomeClass3Def): SomeClass3 {
        const class2Object = def?.class2Object ?? {}
        const class2List = def?.class2List ?? []
        const someEnum = def?.someEnum ?? SomeEnum.VALUE_A

        return new SomeClass3(
            someClass2(class2Object),
            class2List.map(it => someClass2(it)),
            someEnum,
        )
    }

    export interface SomeClass4Def {
        otherId?: string,
        otherClass?: OtherModule.Builder.OtherClassDef,
        otherIdList?: string[],
        otherClassList?: OtherModule.Builder.OtherClassDef[],
    }
    export function someClass4(def?: SomeClass4Def): SomeClass4 {
        const otherId = def?.otherId ?? "someValue"
        const otherClass = def?.otherClass ?? {}
        const otherIdList = def?.otherIdList ?? []
        const otherClassList = def?.otherClassList ?? []

        return new SomeClass4(
            new OtherId(otherId),
            OtherModule.Builder.otherClass(otherClass),
            otherIdList.map(it => new OtherId(it)),
            otherClassList.map(it => OtherModule.Builder.otherClass(it)),
        )
    }

    export interface SomeClass5Def {
        date?: string,
        dateRange?: TypesModule.Builder.DateRangeDef,
        dateRangeWrapper?: DateRangeWrapperDef,
        someProperty?: SomePropertyDef,
        otherProperty?: OtherModule.Builder.OtherPropertyDef,
    }
    export function someClass5(def?: SomeClass5Def): SomeClass5 {
        const date = def?.date ?? "someValue"
        const dateRange = def?.dateRange ?? {}
        const dateRangeWrapper = def?.dateRangeWrapper ?? {}
        const someProperty = def?.someProperty ?? {}
        const otherProperty = def?.otherProperty ?? {}

        return new SomeClass5(
            TypesModule.CustomTypesMapper.dateCreate(date),
            TypesModule.Builder.dateRange(dateRange),
            dateRangeWrapper(dateRangeWrapper),
            someProperty(someProperty),
            OtherModule.Builder.otherProperty(otherProperty),
        )
    }
}