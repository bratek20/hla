namespace SomeModule.Web {
    export class DateRangeWrapperDto {
        range = new TypesModule.Web.DateRangeDto

        toApi(): DateRangeWrapper {
            return SomeModule.CustomTypesMapper.dateRangeWrapperCreate(
                this.range.toApi(),
            )
        }

        static fromApi(api: DateRangeWrapper): DateRangeWrapperDto {
            const dto = new DateRangeWrapperDto()
            dto.range = TypesModule.Web.DateRangeDto.fromApi(SomeModule.CustomTypesMapper.dateRangeWrapperGetRange(api))
            return dto
        }
    }

    export class SomeClassDto {
        id = STRING
        amount = NUMBER

        toApi(): SomeClass {
            return new SomeClass(
                new SomeId(this.id),
                this.amount,
            )
        }

        static fromApi(api: SomeClass): SomeClassDto {
            const dto = new SomeClassDto()
            dto.id = api.id.value
            dto.amount = api.amount
            return dto
        }
    }

    export class SomeClass2Dto {
        id = STRING
        enabled = BOOLEAN
        names = [STRING]
        ids = [STRING]

        toApi(dto: SomeClass2Dto): SomeClass2 {
            return new SomeClass2(
                new SomeId(dto.id),
                dto.enabled,
                dto.names,
                dto.ids.map(it => new SomeId(it)),
            )
        }

        static fromApi(api: SomeClass2): SomeClass2Dto {
            const dto = new SomeClass2Dto()
            dto.id = api.id.value
            dto.enabled = api.enabled
            dto.names = api.names
            dto.ids = api.ids.map(it => it.value)
            return dto
        }
    }

    export class SomeClass3Dto {
        class2Object = new SomeClass2Dto
        class2List = [new SomeClass2Dto]
        someEnum = STRING

        toApi(dto: SomeClass3Dto): SomeClass3 {
            return new SomeClass3(
                dto.class2Object.toApi(),
                dto.class2List.map(it => it.toApi()),
                SomeEnum.fromName(dto.someEnum).get(),
            )
        }

        static fromApi(api: SomeClass3): SomeClass3Dto {
            const dto = new SomeClass3Dto()
            dto.class2Object = SomeClass2Dto.fromApi(api.class2Object)
            dto.class2List = api.class2List.map(it => SomeClass2Dto.fromApi(it))
            dto.someEnum = api.someEnum.getName()
            return dto
        }
    }

    export class SomeClass4Dto {
        otherId = STRING
        otherClass = new OtherModule.Web.OtherClassDto
        otherIdList = [STRING]
        otherClassList = [new OtherModule.Web.OtherClassDto]

        static toApi(dto: SomeClass4Dto): SomeClass4 {
            return new SomeClass4(
                new OtherId(dto.otherId),
                dto.otherClass.toApi(),
                dto.otherIdList.map(it => new OtherId(it)),
                dto.otherClassList.map(it => it.toApi()),
            )
        }

        static fromApi(api: SomeClass4): SomeClass4Dto {
            const dto = new SomeClass4Dto()
            dto.otherId = api.otherId.value
            dto.otherClass = OtherModule.Web.OtherClassDto.fromApi(api.otherClass)
            dto.otherIdList = api.otherIdList.map(it => it.value)
            dto.otherClassList = api.otherClassList.map(it => OtherModule.Web.OtherClassDto.fromApi(it))
            return dto
        }
    }

    export class SomeClass5Dto {
        date = STRING
        dateRange = new TypesModule.Web.DateRangeDto
        dateRangeWrapper = new DateRangeWrapperDto
        someProperty = new SomeProperty
        otherProperty = new OtherModule.OtherProperty

        static toApi(dto: SomeClass5Dto): SomeClass5 {
            return new SomeClass5(
                TypesModule.CustomTypesMapper.dateCreate(dto.date),
                dto..toApi(dto.dateRange),
                DateRangeWrapperDto.toApi(dto.dateRangeWrapper),
                dto.someProperty,
                dto.otherProperty,
            )
        }

        static fromApi(api: SomeClass5): SomeClass5Dto {
            const dto = new SomeClass5Dto()
            dto.date = TypesModule.CustomTypesMapper.dateGetValue(api.date)
            dto.dateRange = TypesModule.Web.DateRangeDto.fromApi(api.dateRange)
            dto.dateRangeWrapper = DateRangeWrapperDto.fromApi(api.dateRangeWrapper)
            dto.someProperty = api.someProperty
            dto.otherProperty = api.otherProperty
            return dto
        }
    }
}