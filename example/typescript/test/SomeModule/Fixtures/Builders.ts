// DO NOT EDIT! Autogenerated by HLA tool

namespace SomeModule.Builder {
    export interface DateRangeWrapperDef {
        range?: TypesModule.Builder.DateRangeDef,
    }
    export function dateRangeWrapper(def?: DateRangeWrapperDef): DateRangeWrapper {
        const range = def?.range ?? {}

        return SomeModule.CustomTypesMapper.dateRangeWrapperCreate(
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
        names?: string[],
        ids?: string[],
        enabled?: boolean,
    }
    export function someClass2(def?: SomeClass2Def): SomeClass2 {
        const id = def?.id ?? "someValue"
        const names = def?.names ?? []
        const ids = def?.ids ?? []
        const enabled = def?.enabled ?? true

        return new SomeClass2(
            new SomeId(id),
            names,
            ids.map(it => new SomeId(it)),
            enabled,
        )
    }

    export interface SomeClass3Def {
        class2Object?: SomeClass2Def,
        someEnum?: SomeEnum,
        class2List?: SomeClass2Def[],
    }
    export function someClass3(def?: SomeClass3Def): SomeClass3 {
        const class2Object = def?.class2Object ?? {}
        const someEnum = def?.someEnum ?? SomeEnum.VALUE_A
        const class2List = def?.class2List ?? []

        return new SomeClass3(
            someClass2(class2Object),
            someEnum,
            class2List.map(it => someClass2(it)),
        )
    }

    export interface SomeClass4Def {
        otherId?: number,
        otherClass?: OtherModule.Builder.OtherClassDef,
        otherIdList?: number[],
        otherClassList?: OtherModule.Builder.OtherClassDef[],
    }
    export function someClass4(def?: SomeClass4Def): SomeClass4 {
        const otherId = def?.otherId ?? 0
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
        const date = def?.date ?? "01/01/1970 00:00"
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

    export interface SomeClass6Def {
        someClassOpt?: SomeClassDef,
        optString?: string,
        sameClassList?: SomeClass6Def[],
    }
    export function someClass6(def?: SomeClass6Def): SomeClass6 {
        const someClassOpt = def?.someClassOpt ?? undefined
        const optString = def?.optString ?? undefined
        const sameClassList = def?.sameClassList ?? []

        return new SomeClass6(
            Optional.of(someClassOpt).map(it => someClass(it)),
            Optional.of(optString),
            sameClassList.map(it => someClass6(it)),
        )
    }
}