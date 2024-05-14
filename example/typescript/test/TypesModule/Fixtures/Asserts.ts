namespace TypesModule.Assert {
    export interface ExpectedDateRange {
        from?: string,
        to?: string,
    }
    export function dateRange(given: DateRange, expected: ExpectedDateRange) {
        if (expected.from !== undefined) {
            AssertEquals(CustomTypesMapper.dateGetValue(CustomTypesMapper.dateRangeGetFrom(given)), expected.from)
        }

        if (expected.to !== undefined) {
            AssertEquals(CustomTypesMapper.dateGetValue(CustomTypesMapper.dateRangeGetTo(given)), expected.to)
        }
    }
}