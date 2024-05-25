// DO NOT EDIT! Autogenerated by HLA tool

namespace TypesModule.Web {
    export class DateRangeDto {
        from = STRING
        to = STRING

        toApi(): DateRange {
            return TypesModule.CustomTypesMapper.dateRangeCreate(
                TypesModule.CustomTypesMapper.dateCreate(this.from),
                TypesModule.CustomTypesMapper.dateCreate(this.to),
            )
        }

        static fromApi(api: DateRange): DateRangeDto {
            const dto = new DateRangeDto()
            dto.from = TypesModule.CustomTypesMapper.dateGetValue(TypesModule.CustomTypesMapper.dateRangeGetFrom(api))
            dto.to = TypesModule.CustomTypesMapper.dateGetValue(TypesModule.CustomTypesMapper.dateRangeGetTo(api))
            return dto
        }
    }
}