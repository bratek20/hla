// DO NOT EDIT! Autogenerated by HLA tool

namespace TypesModule {
    export function diffDate(given: Date, expected: string, path: string = ""): string {
        if (TypesModule.CustomTypesMapper.dateGetValue(given) != expected) { return `${path}value ${TypesModule.CustomTypesMapper.dateGetValue(given)} != ${expected}` }
        return ""
    }

    export interface ExpectedDateRange {
        from?: string,
        to?: string,
    }
    export function diffDateRange(given: DateRange, expected: ExpectedDateRange, path: string = ""): string {
        const result: string[] = []

        if (expected.from !== undefined) {
            if (diffDate(TypesModule.CustomTypesMapper.dateRangeGetFrom(given), expected.from) != "") { result.push(diffDate(TypesModule.CustomTypesMapper.dateRangeGetFrom(given), expected.from, `${path}from.`)) }
        }

        if (expected.to !== undefined) {
            if (diffDate(TypesModule.CustomTypesMapper.dateRangeGetTo(given), expected.to) != "") { result.push(diffDate(TypesModule.CustomTypesMapper.dateRangeGetTo(given), expected.to, `${path}to.`)) }
        }

        return result.join("\n")
    }
}