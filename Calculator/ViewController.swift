//
//  ViewController.swift
//  Calculator
//
//  Created by Moss on 4/24/16.
//  Copyright © 2016 Moss. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var display: UILabel!
    
    var userIsInMiddleOfTypeingANumber = false
    
    var operandStack = Array<Double>()
    
    var displayValue: Double {
        get {
            return NSNumberFormatter().numberFromString(display.text!)!.doubleValue
        }
        set {
            display.text = "\(newValue)"
            userIsInMiddleOfTypeingANumber = false
        }
    }


    @IBAction func operate(sender: UIButton) {
        let operation = sender.currentTitle!
        if (userIsInMiddleOfTypeingANumber) {
            enter()
        }
        switch operation {
        case "+": performOperation({$0 + $1})
        case "−": performOperation({$1 - $0})
        case "×": performOperation({$0 * $1})
        case "÷": performOperation({$1 / $0})
        case "√": performOperation1 {sqrt($0)}
        default: break
        }
        
    
    }
    
    func performOperation(operation: (Double, Double) -> Double) {
        if (operandStack.count == 2) {
            displayValue = operation(operandStack.removeLast(), operandStack.removeLast())
            enter()
        }
    }
    
    func performOperation1(operation: Double -> Double) {
        if (operandStack.count >= 1) {
            displayValue = operation(operandStack.removeLast())
            enter()
        }
    }
    
    
    @IBAction func appendDigit(sender: UIButton) {
        let digit = sender.currentTitle!
        if userIsInMiddleOfTypeingANumber {
            display.text = display.text! + digit
        } else {
            display.text = digit
            userIsInMiddleOfTypeingANumber = true
        }
    }
    
    @IBAction func enter() {
        userIsInMiddleOfTypeingANumber = false
        operandStack.append(displayValue)
        print("operandStack=\(operandStack)")
    }
    
    
}

