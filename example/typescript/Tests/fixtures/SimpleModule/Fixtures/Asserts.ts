// DO NOT EDIT! Autogenerated by HLA tool

namespace SimpleModule.Assert {
    export function simpleId(given: SimpleId, expected: string) {
        const diff = diffSimpleId(given, expected)
        AssertEquals(diff, "", diff)
    }
}