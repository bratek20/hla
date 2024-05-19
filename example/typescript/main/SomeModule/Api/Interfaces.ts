interface SomeInterface {
    /**
     * @throws { SomeException }
     * @throws { Some2Exception }
     */
    someCommand(id: SomeId, amount: number): void

    /**
     * @throws { SomeException }
     */
    someQuery(id: SomeId): SomeClass
}