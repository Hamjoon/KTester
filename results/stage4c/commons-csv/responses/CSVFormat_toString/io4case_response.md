{
    "group": "toString Method Additional Test Cases",
    "cases": [
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<>",
            "description": "Test the toString method for the POSTGRESQL_CSV format where nullString is an empty string."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false HeaderComments:[\"Comment 1\", \"Comment 2\"] Header:[\"Header1\", \"Header2\"]",
            "description": "Test the toString method for a custom CSVFormat where headerComments and headers are set."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:true",
            "description": "Test the toString method for a custom CSVFormat where skipHeaderRecord is true."
        },
        {
            "input": [],
            "expected": "Delimiter=<;> Escape=<\\> QuoteChar=<'> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for a custom CSVFormat with a semicolon delimiter and single quote as quote character."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<NONE> RecordSeparator=<\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for a custom CSVFormat where quoteMode is set to NONE."
        },
        {
            "input": [],
            "expected": "Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<ALL> RecordSeparator=<\\r> SkipHeaderRecord:false",
            "description": "Test the toString method for a custom CSVFormat with pipe delimiter, carriage return as record separator, and all fields quoted."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false IgnoreHeaderCase:ignored",
            "description": "Test the toString method for a custom CSVFormat where ignoreHeaderCase is true."
        },
        {
            "input": [],
            "expected": "Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false EmptyLines:ignored",
            "description": "Test the toString method for a custom CSVFormat with tab delimiter and ignore empty lines set to true."
        }
    ]
}