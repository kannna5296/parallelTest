CREATE TABLE [dbo].[app_user](
    [id] [int] IDENTITY(1, 1) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    [password] [nvarchar](50) NOT NULL,
    [created_at] [datetime2](2) NOT NULL,
    [updated_at] [datetime2](2) NOT NULL,
    PRIMARY KEY CLUSTERED([id] ASC) WITH(
        PAD_INDEX = OFF,
        STATISTICS_NORECOMPUTE = OFF,
        IGNORE_DUP_KEY = OFF,
        ALLOW_ROW_LOCKS =
    ON,
        ALLOW_PAGE_LOCKS =
    ON
    )
ON  [PRIMARY]
)
ON  [PRIMARY]