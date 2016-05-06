//
//  ColculatorBrain.swift
//  Calculator
//
//  Created by Moss on 5/6/16.
//  Copyright © 2016 Moss. All rights reserved.
//

import Foundation

class CalculatorBrain {
    
    enum Operation {
        case Constant(Double)
        case UnaryOperation((Double) -> Double)
        case BinaryOperation((Double, Double) -> Double)
        case Equals
    }
    
    struct PendingBinaryOperationInfo {
        var binaryFunction: (Double, Double) -> Double
        var firstOperand: Double
    }
    
    private var accumulator = 0.0
    
    private var pending:PendingBinaryOperationInfo?
    
    var result: Double {
        get {
            return accumulator;
        }
    }
    
    private var operations = [
        "π": Operation.Constant(M_PI),
        "e": Operation.Constant(M_E),
        "√": Operation.UnaryOperation(sqrt),
        "cos": Operation.UnaryOperation(cos),
        "+": Operation.BinaryOperation {$0 + $1},
        "−": Operation.BinaryOperation {$0 - $1},
        "×": Operation.BinaryOperation {$0 * $1},
        "÷": Operation.BinaryOperation {$0 / $1},
        "=": Operation.Equals
    ]
    
    private func executePendingBinaryOperation() {
        if pending != nil {
            accumulator = pending!.binaryFunction(pending!.firstOperand, accumulator)
            pending = nil
        }
    }
    
    func setOperand(operand: Double) {
        accumulator = operand
    }
    
    func performOperation(symbol: String) {
        if let operation = operations[symbol] {
            switch operation {
            case .Constant(let value):
                accumulator = value
            case .UnaryOperation(let function):
                accumulator = function(accumulator)
            case .BinaryOperation(let function):
                executePendingBinaryOperation()
                pending = PendingBinaryOperationInfo(binaryFunction: function, firstOperand: accumulator)
            case .Equals:
                executePendingBinaryOperation()
            }
        }
    }
    
}