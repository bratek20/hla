// DO NOT EDIT! Autogenerated by HLA tool

namespace OtherModule.Assert {
    export function otherId(given: OtherId, expected: number) {
        const diff = Diff.otherId(given, expected)
        AssertEquals(diff, "", diff)
    }

    export function otherProperty(given: OtherProperty, expected: Diff.ExpectedOtherProperty) {
        const diff = Diff.otherProperty(given, expected)
        AssertEquals(diff, "", diff)
    }

    export function otherClass(given: OtherClass, expected: Diff.ExpectedOtherClass) {
        const diff = Diff.otherClass(given, expected)
        AssertEquals(diff, "", diff)
    }
}