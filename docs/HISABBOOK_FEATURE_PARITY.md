# HisabBook feature parity plan for Apple Area Shop

This plan is based on the visible Bengali/English resource strings extracted from the supplied HisabBook APK and the current Apple Area Shop source tree. It is a feature inventory, not a claim that these modules are implemented yet.

## Current Apple Area Shop baseline
- Dashboard and shop metrics
- Product stock with device details, IMEI, variants, condition and supplier/source
- Sales, due collection, invoice/memo and warranty information
- Product returns
- Expenses and income entries
- Employees and salary payments
- Reports
- User roles, shop settings, language/theme/text sizing
- GitHub repository backup/sync

## HisabBook feature groups to add

### 1. Sales and customer management
- Customer directory, customer types, customer profile and transaction history
- Customer ledger, customer due and receivable-invoice reports
- Sales orders, sales invoices, sales challans and delivery information
- Quotations, quotation printing and conversion to sale
- Sales returns, return invoices and refund settlement
- Item-level and percentage discounts
- Customer advances, payment collection and partial payments
- Customer NID/document attachment and signatures

### 2. Purchasing and supplier management
- Supplier directory, supplier types, supplier profile and supplier ledger
- Purchase orders, standard purchase, quick purchase and purchase invoices
- Purchase returns and supplier refund/settlement
- Supplier due and overdue purchase reports
- Purchase requests and approval workflow
- Supplier advances and payment collection

### 3. Inventory and product operations
- Product categories, groups, brands, units and unit conversions
- Bulk product import/export using Excel/CSV
- Barcode generation, scanning and label printing
- Serial/IMEI tracking per unit, uniqueness validation and warranty dates
- Stock in/out, stock history and stock valuation
- Multi-terminal stock transfer and transfer history
- Damaged-product reporting and damage stock
- Product exchange/trade-in with replacement invoice and settlement
- Warranty check, claims and warranty reports
- Product variants, batches/lots and assembled products

### 4. Accounting and cash/bank
- Chart of accounts and account detail types
- Cash and bank accounts, account ledger and general ledger
- Cash-in, cash-out and cash-flow reports
- Fund transfers between accounts
- Capital deposits and withdrawals with vouchers
- Customer/supplier due collection and payment
- Profit & loss, income, cost and expense reports
- Transaction references, audit trail and reversals

### 5. Expenses and operational costs
- Expense categories and multi-line expenses
- Expense account selection and attachments
- Expense reports, cost reports and cash-flow integration

### 6. Employees, attendance and payroll
- Employee profiles, roles, organogram and employee ledger
- Attendance types, attendance list and attendance reports
- Leave applications, approval and leave history
- Salary grades/settings, payroll processing and approval
- Salary payments, payslips, deductions, advances and reversals
- Employee targets and performance reports

### 7. Delivery and communications
- Delivery agencies, delivery agents and delivery-area setup
- Delivery challan, tracking, timeline, status and delivery charges
- SMS manager, templates, balance and history
- Sales/purchase SMS notifications
- Share via WhatsApp, email and other Android share targets

### 8. Reports and dashboards
- Daily, monthly and date-range summaries
- Sales, purchase, product, stock, due, customer, supplier and employee reports
- Cash-flow, income, expense, cost and profit/loss reports
- PDF and Excel/CSV export

### 9. Administration and configuration
- Multi-user roles and granular permissions
- Multi-terminal access assignment and terminal switching
- Branch/shop profile and business information
- Invoice/quotation/challan layouts and terms
- Thermal/Bluetooth/label printer settings and test print
- Notification settings, app language and appearance
- Data backup, restore, sync and audit/recovery controls

## Implementation sequence

1. **Data foundation:** customers, suppliers, accounts, purchases, payments and transaction ledger. Use non-destructive Room migrations; never discard existing local data.
2. **Core business flows:** purchase and supplier due; customer ledger and due collection; sale/return/exchange; inventory serials and warranty.
3. **Accounting:** cash/bank, cash-in/out, capital, transfers, cash flow and profit/loss.
4. **Operations:** damage, stock transfer, delivery, barcode/label printing and bulk import/export.
5. **People:** attendance, leave, employee ledger, payroll and targets.
6. **Reports and administration:** full report catalog, SMS integrations, terminal/branch permissions, invoice layouts and exports.

## Important technical constraints
- The supplied HisabBook APK is a Flutter application; Apple Area Shop is Kotlin/Jetpack Compose with Room. Reusing its compiled app code is not a safe or maintainable integration path.
- The APK's visible strings identify feature names and workflows, but do not provide its server-side source code, database schema, credentials, or business API implementation.
- Any SMS, cloud sync, multi-terminal, delivery-provider or online accounting workflow needs a backend/service configuration. These must be implemented with the user's own service credentials; no credentials should be copied from an APK.
- Keep the original `main` branch untouched. Implement and validate changes on this feature branch, then review through a pull request.
- A module is complete only when its data model, persistence, validation, UI, reports and tests work—not merely when a menu item is visible.
