// DO NOT EDIT! Autogenerated by HLA tool

namespace OtherModule.Builder {
    export interface OtherPropertyDef {
        id?: number,
        name?: string,
    }
    export function otherProperty(def?: OtherPropertyDef): OtherProperty {
        const id = def?.id ?? 0
        const name = def?.name ?? "someValue"

        return OtherProperty.create(
            id,
            name,
        )
    }

    export interface OtherClassDef {
        id?: number,
        amount?: number,
    }
    export function otherClass(def?: OtherClassDef): OtherClass {
        const id = def?.id ?? 0
        const amount = def?.amount ?? 0

        return new OtherClass(
            new OtherId(id),
            amount,
        )
    }
}