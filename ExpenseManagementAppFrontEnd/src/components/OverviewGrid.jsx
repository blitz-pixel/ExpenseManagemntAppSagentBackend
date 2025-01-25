import { WalletCards, Receipt, FileText, FolderKanban } from "lucide-react"
import Link from "next/link"

export default function OverviewGrid() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-6 p-6">
      <Link href="/income">
        <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-2xl font-bold">INCOME OVERVIEW</h2>
            <WalletCards className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
          </div>
        </div>
      </Link>

      <Link href="/expense">
        <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-2xl font-bold">EXPENSE OVERVIEW</h2>
            <Receipt className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
          </div>
        </div>
      </Link>

      <Link href="/report">
        <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-2xl font-bold">REPORT</h2>
            <FileText className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
          </div>
        </div>
      </Link>

      <Link href="/categories">
        <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-2xl font-bold">CATEGORIES</h2>
            <FolderKanban className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
          </div>
        </div>
      </Link>
    </div>
  )
}

