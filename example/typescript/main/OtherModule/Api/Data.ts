// DO NOT EDIT! Autogenerated by HLA tool

class OtherData {
    private id = NUMBER

    static create(
        id: OtherId,
    ): OtherData {
        const instance = new OtherData()
        instance.id = id.value
        return instance
    }

    getId(): OtherId {
        return new OtherId(this.id)
    }

    setId(id: OtherId): void {
        this.id = id.value
    }
}

namespace OtherModule {
    export const OTHER_DATA_KEY = new ObjectPropertyKey(
        "otherData",
        OtherData
    )
}