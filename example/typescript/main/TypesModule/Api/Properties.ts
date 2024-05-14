namespace TypesModule {
    export class DateRangeProperty {
        private from = STRING
        private to = STRING

        static create(
            from: string,
            to: string,
        ): DateRangeProperty {
            const instance = new DateRangeProperty()
            instance.from = from
            instance.to = to
            return instance
        }

        getFrom(): Date {
            return CustomTypesMapper.dateCreate(this.from)
        }

        getTo(): Date {
            return CustomTypesMapper.dateCreate(this.to)
        }
    }
}